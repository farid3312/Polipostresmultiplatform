package com.example.polipostresmultiplatform

import androidx.compose.runtime.*
import com.example.polipostresmultiplatform.model.Dessert
import com.example.polipostresmultiplatform.model.User
import com.example.polipostresmultiplatform.theme.PoliPostresTheme
import com.example.polipostresmultiplatform.auth.LoginScreen
import com.example.polipostresmultiplatform.auth.RegisterScreen
import com.example.polipostresmultiplatform.dashboard.MainScaffold
import com.example.polipostresmultiplatform.dashboard.TabScreen
import com.example.polipostresmultiplatform.info.CreditsScreen
import com.example.polipostresmultiplatform.data.DessertRepository
import com.example.polipostresmultiplatform.data.UserRepository
import com.example.polipostresmultiplatform.data.SessionRepository

enum class Screen { Login, Register, MainTabs, Credits }

@Composable
fun App(
    dessertRepository: DessertRepository,
    userRepository: UserRepository,
    sessionRepository: SessionRepository,
    onSelectImage: () -> Unit = {},
    selectedImageUri: String? = null
) {
    var currentScreen by remember { mutableStateOf(Screen.Login) }
    var currentTab by remember { mutableStateOf(TabScreen.Inventory) }

    // Lista en memoria solo como "copia" de lo que hay en la BD
    val desserts = remember { mutableStateListOf<Dessert>() }

    PoliPostresTheme {

        // Cargar datos iniciales desde el repositorio y restaurar sesión
        LaunchedEffect(Unit) {
            // 1) Cargar postres desde la BD
            val dbDesserts = dessertRepository.getAllDesserts()
            if (dbDesserts.isEmpty()) {
                // Si está vacío, insertamos los que tenías hardcodeados
                val initialDesserts = listOf(
                    Dessert("1", "Cascadapostre", 20, 8),
                    Dessert("2", "Gustavo", 27, 0),
                    Dessert("3", "Sabrosón", 22, 5)
                )
                initialDesserts.forEach { dessertRepository.insertDessert(it) }
                desserts.clear()
                desserts.addAll(initialDesserts)
            } else {
                desserts.clear()
                desserts.addAll(dbDesserts)
            }

            // 2) Asegurarse de que exista el usuario Admin por defecto
            val adminEmail = "admin@polipostres.com"
            if (!userRepository.existsByEmail(adminEmail)) {
                userRepository.insertUser(
                    User(
                        name = "Admin",
                        email = adminEmail,
                        password = "1234"
                    )
                )
            }

            // 3) Restaurar sesión si hay un usuario guardado
            val emailSession = sessionRepository.getLoggedInUserEmail()
            if (emailSession != null) {
                val user = userRepository.findByEmail(emailSession)
                if (user != null) {
                    currentScreen = Screen.MainTabs
                } else {
                    // Si no existe el usuario, limpiamos la sesión
                    sessionRepository.clearSession()
                }
            }
        }

        when (currentScreen) {
            Screen.Login -> LoginScreen(
                onLogin = { email, password ->
                    val emailTrim = email.trim()
                    val user = userRepository.findByEmailAndPassword(emailTrim, password)
                    if (user != null) {
                        // Guardar sesión
                        sessionRepository.setLoggedInUserEmail(user.email)
                        currentScreen = Screen.MainTabs
                        true   // login correcto
                    } else {
                        false  // login incorrecto
                    }
                },
                onNavigateToRegister = { currentScreen = Screen.Register }
            )

            Screen.Register -> RegisterScreen(
                onRegister = { name, email, password ->
                    val emailNorm = email.trim().lowercase()
                    // Evitar correos repetidos usando el repositorio
                    if (!userRepository.existsByEmail(emailNorm)) {
                        userRepository.insertUser(
                            User(
                                name = name.trim(),
                                email = emailNorm,
                                password = password
                            )
                        )
                    }
                },
                onBack = { currentScreen = Screen.Login }
            )

            Screen.Credits -> CreditsScreen(
                onBack = { currentScreen = Screen.MainTabs }
            )

            Screen.MainTabs -> MainScaffold(
                currentTab = currentTab,
                onTabSelected = { currentTab = it },
                onLogout = {
                    sessionRepository.clearSession()
                    currentScreen = Screen.Login
                },
                onCredits = { currentScreen = Screen.Credits },
                desserts = desserts,
                onAddDessert = { dessert ->
                    // Guardar en BD y actualizar lista de UI
                    dessertRepository.insertDessert(dessert)
                    desserts.add(dessert)
                },
                onSellDessert = { id ->
                    val index = desserts.indexOfFirst { it.id == id }
                    if (index != -1 && desserts[index].stock > 0) {
                        val item = desserts[index]
                        val updated = item.copy(
                            stock = item.stock - 1,
                            sold = item.sold + 1
                        )
                        dessertRepository.updateDessert(updated)
                        desserts[index] = updated
                    }
                },
                onDeleteDessert = { id ->
                    dessertRepository.deleteDessertById(id)
                    desserts.removeAll { it.id == id }
                },
                onIncreaseStock = { id ->
                    val index = desserts.indexOfFirst { it.id == id }
                    if (index != -1) {
                        val item = desserts[index]
                        val updated = item.copy(stock = item.stock + 1)
                        dessertRepository.updateDessert(updated)
                        desserts[index] = updated
                    }
                },
                onSelectImage = onSelectImage
            )
        }
    }
}
