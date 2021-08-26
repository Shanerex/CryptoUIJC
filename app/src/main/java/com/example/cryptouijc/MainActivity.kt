package com.example.cryptouijc

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.cryptouijc.ui.BottomNavigationBar
import com.example.cryptouijc.ui.CryptoDetailsScreen
import com.example.cryptouijc.ui.HomeScreen
import com.example.cryptouijc.ui.TransactionScreen
import com.example.cryptouijc.ui.theme.CryptoUIJCTheme
import com.example.cryptouijc.ui.theme.Purple500
import com.example.cryptouijc.utils.NavigationItems
import com.example.cryptouijc.utils.Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoUIJCTheme {
                var navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            onItemSelected = {
                                when(it) {
                                    NavigationItems.Home -> navController.navigate(Screen.HomeScreen.route)
                                    NavigationItems.Portfolio -> navController.navigate(Screen.PortfolioScreen.route)
                                    NavigationItems.Prices -> navController.navigate(Screen.PricesScreen.route)
                                    NavigationItems.Settings -> navController.navigate(Screen.SettingsScreen.route)
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(Screen.TradeScreen.route)
                            },
                            backgroundColor = Purple500,
                            contentColor = Color.White,
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.transaction),
                                contentDescription = "Transaction Button"
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true
                ) {
                    Navigation(navController)
                }
            }
        }
    }

    @Composable
    fun Navigation(
        navController: NavHostController
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(
                route = Screen.HomeScreen.route
            ) {
                HomeScreen() { currencyCode ->
                    navController.navigate(Screen.CryptoDetailsScreen.route + "/$currencyCode")
                }
            }
            composable(
                route = Screen.CryptoDetailsScreen.route + "/{currencyCode}",
                arguments = listOf(
                    navArgument(name = "currencyCode") {
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                val currencyCode = entry.arguments?.getString("currencyCode")!!
                CryptoDetailsScreen(
                    currencyCode = currencyCode,
                    onBackArrowPressed = {
                        navController.popBackStack()
                    },
                    onButtonClick = {
                        navController.navigate(Screen.TransactionScreen.route + "/$currencyCode")
                    }
                )
            }
            composable(
                route = Screen.TransactionScreen.route + "/{currencyCode}",
                arguments = listOf(
                    navArgument(name = "currencyCode") {
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                val currencyCode = entry.arguments?.getString("currencyCode")!!
                TransactionScreen(
                    onBackArrowPressed = {
                        navController.popBackStack()
                    },
                    currencyCode = currencyCode,
                    onTradeButtonClick = {

                    }
                )
            }
        }
    }
}

