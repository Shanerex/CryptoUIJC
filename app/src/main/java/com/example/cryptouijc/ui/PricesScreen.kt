package com.example.cryptouijc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.ui.Prices_components.*
import com.example.cryptouijc.ui.theme.BannerColor
import com.example.cryptouijc.ui.theme.LightGray1
import com.example.cryptouijc.utils.SampleData

@ExperimentalComposeUiApi
@Composable
fun PricesScreen(
    onBackButtonPressed: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = LightGray1
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 50.dp)
        ) {
            TopRow(
                onBackArrowPressed = onBackButtonPressed,
                isStarred = true
            )
            SearchSection()
            val selectedIndex = mutableStateOf(0)
            selectedIndex.value = chipSection()
            Spacer(modifier = Modifier.height(10.dp))
            when(selectedIndex.value) {
                0 -> AllSection(
                    onItemClick = onItemClick
                )
                1 -> FollowingSection(
                    onItemClick = onItemClick
                )
                2 -> CryptoSection(
                    onItemClick = onItemClick
                )
                3 -> UtilityTokensSection(
                    onItemClick = onItemClick
                )
                4 -> StableCoinsSection(
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
private fun chipSection(): Int {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier.padding(
        top = 10.dp,
        end = 8.dp
    )) {
        items(count = SampleData.topChipsName.size) { index ->  
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        selectedIndex = index
                    }
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        if (selectedIndex == index) BannerColor
                        else Color.White
                    )
            ) {
                Text(
                    text = SampleData.topChipsName[index],
                    modifier = Modifier.padding(
                        vertical = 6.dp,
                        horizontal = 10.dp
                    ),
                    color = if(selectedIndex == index) {
                        Color.White
                    } else {
                        Color.Black
                    },
                    fontSize = 14.sp
                )
            }
        }
    }
    return selectedIndex
}




