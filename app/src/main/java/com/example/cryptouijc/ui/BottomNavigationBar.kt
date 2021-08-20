package com.example.cryptouijc.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptouijc.ui.theme.Purple500
import com.example.cryptouijc.ui.theme.Typography
import com.example.cryptouijc.utils.NavigationItems

@Composable
fun BottomNavigationBar(
    modifier : Modifier = Modifier,
    onItemSelected: (NavigationItems) -> Unit
) {
    val navigationItems = listOf<NavigationItems>(
        NavigationItems.Home,
        NavigationItems.Portfolio,
        NavigationItems.Transaction,
        NavigationItems.Prices,
        NavigationItems.Settings
    )

    var selectedItemIndex by remember {
        mutableStateOf(0)
    }

    BottomNavigation(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = RectangleShape
            ),
        contentColor = Purple500,
        backgroundColor = Color.White
    ) {
        navigationItems.forEach {
            BottomNavigationItem(
                selected = (selectedItemIndex == navigationItems.indexOf(it)),
                onClick = {
                    selectedItemIndex = navigationItems.indexOf(it)
                    onItemSelected(it)
                },
                icon = {
                    if(it.title == NavigationItems.Transaction.title){}
                    else {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = it.title
                        )
                    }
                },
                label = {
                    Text(
                        text = it.title,
                        style = Typography.h5
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = Purple500,
                unselectedContentColor = Color.Black
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPrev() {
    BottomNavigationBar(){}
}