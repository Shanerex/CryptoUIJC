package com.example.cryptouijc.utils

import com.example.cryptouijc.R

sealed class NavigationItems(
    var route: String,
    var icon: Int,
    var title: String
) {
    object Home: NavigationItems("home_nav_item", R.drawable.home, "Home")
    object Portfolio: NavigationItems("portfolio_nav_item", R.drawable.pie_chart, "Portfolio")
    object Transaction: NavigationItems("transaction_nav_item", R.drawable.transaction, "")
    object Prices: NavigationItems("prices_nav_item", R.drawable.line_graph, "Prices")
    object Settings: NavigationItems("settings_nav_item", R.drawable.settings, "Settings")

}
