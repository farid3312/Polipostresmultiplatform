package com.example.polipostresmultiplatform

import androidx.compose.runtime.*
import com.example.polipostresmultiplatform.model.Dessert
import com.example.polipostresmultiplatform.theme.PoliPostresTheme
import com.example.polipostresmultiplatform.ui.auth.LoginScreen
import com.example.polipostresmultiplatform.ui.auth.RegisterScreen
import com.example.polipostresmultiplatform.ui.dashboard.MainScaffold
import com.example.polipostresmultiplatform.ui.dashboard.TabScreen
import com.example.polipostresmultiplatform.ui.info.CreditsScreen

enum class Screen { Login, Register, MainTabs, Credits }

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.Login) }
    var currentTab by remember { mutableStateOf(TabScreen.Inventory) }

    // Estado compartido simple
    val desserts = remember { mutableStateListOf(
        Dessert("1", "Cascadapostre", 20, 8),
        Dessert("2", "Gustavo", 27, 0),
        Dessert("3", "Sabrosón", 22, 5)
    ) }

    PoliPostresTheme {
        when (currentScreen) {
            Screen.Login -> LoginScreen(
                onLoginSuccess = { currentScreen = Screen.MainTabs },
                onNavigateToRegister = { currentScreen = Screen.Register }
            )
            Screen.Register -> RegisterScreen(
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
                        desserts[index] = item.copy(stock = item.stock - 1, sold = item.sold + 1)
                    }
                },
                onDeleteDessert = { id -> desserts.removeIf { it.id == id } }
            )
        }
    }
}