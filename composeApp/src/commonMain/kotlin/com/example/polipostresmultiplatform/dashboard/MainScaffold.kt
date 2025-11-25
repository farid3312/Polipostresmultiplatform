package com.example.polipostresmultiplatform.dashboard


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polipostresmultiplatform.model.Dessert
import com.example.polipostresmultiplatform.theme.DarkBackground
import com.example.polipostresmultiplatform.theme.PinkAccent
import com.example.polipostresmultiplatform.theme.SurfaceColor
import com.example.polipostresmultiplatform.theme.WhiteText

enum class TabScreen { Inventory, Sales, Metrics }

@Composable
fun MainScaffold(
    currentTab: TabScreen,
    onTabSelected: (TabScreen) -> Unit,
    onLogout: () -> Unit,
    onCredits: () -> Unit,
    desserts: List<Dessert>,
    onAddDessert: (Dessert) -> Unit,
    onSellDessert: (String) -> Unit,
    onDeleteDessert: (String) -> Unit,
    onIncreaseStock: (String) -> Unit,
    onSelectImage: () -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onLogout) {
                    Icon(
                        Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = null,
                        tint = WhiteText
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Salir", color = WhiteText)
                }
                Text(
                    text = "PoliPostres",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                IconButton(onClick = onCredits) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "Créditos",
                        tint = WhiteText
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = DarkBackground) {
                NavigationBarItem(
                    selected = currentTab == TabScreen.Inventory,
                    onClick = { onTabSelected(TabScreen.Inventory) },
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Inventario") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PinkAccent,
                        indicatorColor = SurfaceColor
                    )
                )
                NavigationBarItem(
                    selected = currentTab == TabScreen.Sales,
                    onClick = { onTabSelected(TabScreen.Sales) },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text("Vender") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PinkAccent,
                        indicatorColor = SurfaceColor
                    )
                )
                NavigationBarItem(
                    selected = currentTab == TabScreen.Metrics,
                    onClick = { onTabSelected(TabScreen.Metrics) },
                    icon = { Icon(Icons.Default.Equalizer, contentDescription = null) },
                    label = { Text("Métricas") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PinkAccent,
                        indicatorColor = SurfaceColor
                    )
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (currentTab) {
                TabScreen.Inventory -> InventoryScreen(
                    desserts = desserts,
                    onAdd = onAddDessert,
                    onDelete = onDeleteDessert,
                    onIncreaseStock = onIncreaseStock,
                    onSelectImage = onSelectImage
                )
                TabScreen.Sales -> SalesScreen(
                    desserts = desserts,
                    onSell = onSellDessert
                )
                TabScreen.Metrics -> MetricsScreen(
                    desserts = desserts
                )
            }
        }
    }
}
