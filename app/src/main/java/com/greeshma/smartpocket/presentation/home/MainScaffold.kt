package com.greeshma.smartpocket.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greeshma.smartpocket.presentation.navigation.MainNavGraph
import com.greeshma.smartpocket.presentation.navigation.Screen
import com.greeshma.smartpocket.ui.theme.SmartPocketTheme

@Composable
fun MainScaffold(
    rootNavController: NavHostController
) {
    Log.d("NAV", "MAIN SCAFFOLD")
    val mainNavController = rememberNavController()

    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(

        topBar = {
//            TopBar()
        },

        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                BottomBar(
                    currentRoute = currentRoute,
                    onClick = {
                        mainNavController.navigate(it.route)
                    },
                )

                FloatingActionButton(
                    onClick = {
                        rootNavController.navigate(Screen.AddItem.route)
                    },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(
                            x = (0).dp,
                            y = (-30).dp
                        )
                        .size(50.dp),
                    shape = CircleShape,

                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Item"
                    )
                    Text(
                        text = "Add Item",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        },

    ) { padding ->

        MainNavGraph(
            navController = mainNavController,
            rootNavController = rootNavController,
            padding = padding
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartPocketTheme {
        MainScaffold(
        rememberNavController(),
        )
    }
}