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

enum class Screen { Login, Register, MainTabs, Credits }

@Composable
fun App(onSelectImage: () -> Unit = {}) {
    var currentScreen by remember { mutableStateOf(Screen.Login) }
    var currentTab by remember { mutableStateOf(TabScreen.Inventory) }

    val desserts = remember {
        mutableStateListOf(
            Dessert("1", "Cascadapostre", 20, 8),
            Dessert("2", "Gustavo", 27, 0),
            Dessert("3", "Sabrosón", 22, 5)
        )
    }

    // Lista de usuarios en memoria (Admin + los que se registren)
    val users = remember {
        mutableStateListOf(
            User(name = "Admin", email = "admin@polipostres.com", password = "1234")
        )
    }

    PoliPostresTheme {
        when (currentScreen) {
            Screen.Login -> LoginScreen(
                onLogin = { email, password ->
                    val emailTrim = email.trim()
                    val user = users.firstOrNull {
                        it.email.equals(emailTrim, ignoreCase = true) &&
                                it.password == password
                    }
                    if (user != null) {
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
                    // Evitar correos repetidos
                    if (users.none { it.email.equals(emailNorm, ignoreCase = true) }) {
                        users.add(
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
                onLogout = { currentScreen = Screen.Login },
                onCredits = { currentScreen = Screen.Credits },
                desserts = desserts,
                onAddDessert = { desserts.add(it) },
                onSellDessert = { id ->
                    val index = desserts.indexOfFirst { it.id == id }
                    if (index != -1 && desserts[index].stock > 0) {
                        val item = desserts[index]
                        desserts[index] = item.copy(
                            stock = item.stock - 1,
                            sold = item.sold + 1
                        )
                    }
                },
                onDeleteDessert = { id ->
                    desserts.removeAll { it.id == id }
                },
                onIncreaseStock = { id ->
                    val index = desserts.indexOfFirst { it.id == id }
                    if (index != -1) {
                        val item = desserts[index]
                        desserts[index] = item.copy(stock = item.stock + 1)
                    }
                },
                onSelectImage = onSelectImage
            )
        }
    }
}
