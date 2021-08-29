package com.example.cryptouijc.ui.Prices_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.models.TrendingCurrency
import com.example.cryptouijc.utils.SampleData


@Composable
fun AllSection(onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 10.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Following",
            fontSize = 14.sp
        )

        LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
            itemsIndexed(SampleData.followingTokens) { index: Int, item: TrendingCurrency ->  
                CoinRateItem(
                    currency = item,
                    onItemClick = onItemClick
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Cryptocurrencies",
            fontSize = 14.sp
        )

        LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
            itemsIndexed(SampleData.trendingCurrencies) { index: Int, item: TrendingCurrency ->
                CoinRateItem(
                    currency = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}