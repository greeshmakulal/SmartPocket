package com.greeshma.smartpocket.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.greeshma.smartpocket.data.preferences.UserPreferencesDataStore
import com.greeshma.smartpocket.presentation.add.AddItemRoute
import com.greeshma.smartpocket.presentation.auth.UnlockRoute
import com.greeshma.smartpocket.presentation.home.ExpiringItemsScreen
import com.greeshma.smartpocket.presentation.home.ExpiryCard
import com.greeshma.smartpocket.presentation.home.MainScaffold
import com.greeshma.smartpocket.presentation.home.RecentScreen
import com.greeshma.smartpocket.presentation.setup.SetupRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph() {
    val context = LocalContext.current

    val preferencesDataStore = remember {
        UserPreferencesDataStore(context)
    }

    val userPreferences by preferencesDataStore.userPreferences
        .collectAsState(initial = null)

    // Wait until DataStore gives us the value
    if (userPreferences == null) {
        return
    }

    val startDestination =
        if (userPreferences?.setupCompleted == true) {
            Screen.Main.route
        } else {
            Screen.Setup.route
        }

    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = startDestination
    ) {
        composable(Screen.Setup.route) {

            SetupRoute(
                onSetupComplete = {

                    rootNavController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Setup.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScaffold(rootNavController)
        }

        composable(Screen.AddItem.route) {
            AddItemRoute(
                onBackClick = {
                    rootNavController.popBackStack()
                }
            )
        }
        composable(Screen.Recent.route) {

            RecentScreen(
                onBackClick = {
                    rootNavController.popBackStack()
                }
            )
        }
        composable(Screen.Expiry.route) {

            ExpiringItemsScreen(
                onBackClick = {
                    rootNavController.popBackStack()
                }
            )
        }
        composable(Screen.Unlock.route) {
            UnlockRoute(
                onAuthenticated = {
                    rootNavController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Unlock.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }


    }
}