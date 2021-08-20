package com.example.cryptouijc.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptouijc.R
import com.example.cryptouijc.models.TrendingCurrency
import com.example.cryptouijc.ui.theme.*
import com.example.cryptouijc.utils.SampleData


@Composable
fun HomeScreen(
    onCardClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightGray1
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 50.dp)
        ) {
            HomeScreenContent(onCardClick = onCardClick)
        }
    }
}

@Composable
fun HomeScreenContent(
    onCardClick: (String) -> Unit
) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.notification_white),
            contentDescription = "Bell Button",
            modifier = Modifier
                .padding(top = 40.dp, end = 25.dp)
                .align(Alignment.TopEnd)
                .clickable { }
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        ) {
            val operator = if(SampleData.portfolio.changeType == "I") {
                "+"
            } else {
                "-"
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Your Portfolio Balance",
                    color = Color.White,
                    style = Typography.h3
                )
                Text(
                    text = "£${SampleData.portfolio.balance}",
                    color = Color.White,
                    style = Typography.h1
                )
                Text(
                    text = "$operator${SampleData.portfolio.changes}  Last 24 hours",
                    color = Color.White,
                    style = Typography.h5
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 180.dp)
        ) {
            Text(
                text = "Trending",
                color = Color.White,
                style = Typography.h3,
                modifier = Modifier.padding(12.dp)
            )
            LazyRow {
                itemsIndexed(SampleData.trendingCurrencies) { index, currency ->
                    if(index == 0) {
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    CurrencyCard(
                        currency = currency,
                        onCardClick = onCardClick
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}

@Composable
private fun CurrencyCard(
    currency: TrendingCurrency,
    onCardClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(top = 12.dp)
            .clickable {
                onCardClick(currency.currencyCode)
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 10.dp
    ) {
        Column {
            CurrencyItem(currency = currency)
            ValuesItem(currency = currency)
        }
    }
}

@Composable
private fun CurrencyItem(currency: TrendingCurrency) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = currency.imageRes),
            contentDescription = currency.currencyName,
            modifier = Modifier.size(25.dp)
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = currency.currencyName,
                color = Color.Black,
                style = Typography.h2
            )

            Text(
                text = currency.currencyCode,
                style = Typography.subtitle1,
                color = Gray
            )
        }
    }
}

@Composable
private fun ValuesItem(
    currency: TrendingCurrency
) {
    var changeColor by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                Green
            } else {
                Red
            }
        )
    }

    var changeOperator by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                "+"
            } else {
                "-"
            }
        )
    }

    Text(
        text = "£${currency.currentPrice}",
        style = Typography.h3,
        modifier = Modifier.padding(top = 20.dp)
    )

    Text(
        text = "$changeOperator${currency.changes}%",
        style = Typography.h3,
        modifier = Modifier.padding(top = 5.dp)
    )
}