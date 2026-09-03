package com.greeshma.smartpocket.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen( val route: String,
                     val title: String,
                     val icon: ImageVector
) {

    object Setup : Screen(
        route = "setup",
        title = "Setup",
        icon = Icons.Default.Settings
    )
    object Main: Screen (
        route ="main",
        title = "Main",
        icon = Icons.Default.Home
    )
    object Home: Screen (
        route ="home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Items: Screen (
        route ="items",
        title = "Items", 
        icon = Icons.Default.List
    ) object Settings: Screen (
        route ="settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object AddItem: Screen (
        route ="addItem",
        title = "addItem",
        icon = Icons.Default.Add
    ) object Expiry: Screen (
        route ="Expiry",
        title = "Expiring",
        icon = Icons.Default.Add
    ) object Recent: Screen (
        route ="Recent",
        title = "All Items",
        icon = Icons.Default.Add
    )
    object Unlock : Screen(
        route = "unlock",
        title = "Unlock",
        icon = Icons.Outlined.Fingerprint
    )

companion object
{
    val bottomScreens = listOf(Screen.Home,  Screen.Settings)
}

}