package com.example.cryptouijc.utils

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object PortfolioScreen: Screen("portfolio_screen")
    object TradeScreen: Screen("transaction_screen")
    object PricesScreen: Screen("prices_screen")
    object SettingsScreen: Screen("settings_screen")
    object CryptoDetailsScreen: Screen("crypto_details_screen")
    object TransactionScreen: Screen("transaction_screen")
}
