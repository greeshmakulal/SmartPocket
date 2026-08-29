package com.greeshma.smartpocket.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.greeshma.smartpocket.presentation.Items.Items
import com.greeshma.smartpocket.presentation.add.AddItemScreen
import com.greeshma.smartpocket.presentation.home.ExpiringItemsScreen
import com.greeshma.smartpocket.presentation.home.HomeContent
import com.greeshma.smartpocket.presentation.home.RecentItem
import com.greeshma.smartpocket.presentation.home.RecentScreen
import com.greeshma.smartpocket.presentation.home.ViewAll
import com.greeshma.smartpocket.presentation.settings.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(padding)
    ) {

        composable(Screen.Home.route) {
            HomeContent(
                innerPadding = PaddingValues(0.dp),

                onViewAllExpiry = {
                    rootNavController.navigate(
                        Screen.Expiry.route
                    )
                },

                onViewAllRecent = {
                    rootNavController.navigate(
                        Screen.Recent.route
                    )

                }
            )

        }
        composable(Screen.Items.route) {
            Items()
        }
        composable(Screen.Settings.route) {
            SettingsScreen()

        }


    }

    }
