package com.example.cryptouijc.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.R
import com.example.cryptouijc.models.Transaction
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
                    text = "??${SampleData.portfolio.balance}",
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
                .padding(top = 160.dp)
        ) {
            Text(
                text = "Trending",
                color = Color.White,
                style = Typography.h2,
                modifier = Modifier.padding(12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow {
                itemsIndexed(SampleData.trendingCurrencies) { index, currency ->
                    if(index == 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    CurrencyCard(
                        currency = currency,
                        onCardClick = onCardClick
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(10.dp))

            SetPriceAlertSection()
            InvestingSafetySection()
            TransactionHistorySection()
        }
    }
}

@Composable
fun TransactionHistorySection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp)
        ) {
            Text(
                text = "Transaction History",
                style = Typography.h2
            )

            val transactionHistory = SampleData.transactionHistory
            transactionHistory.forEachIndexed { index, transaction ->
                TransactionItem(transaction)

                Divider(
                    modifier = Modifier.padding(
                            top = 12.dp,
                            bottom = if(index < transactionHistory.size - 1) {
                                12.dp
                            } else {
                                0.dp
                            }
                        )
                )
            }
        }
    }
}

@Composable
private fun TransactionItem(transaction: Transaction) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.transaction),
                contentDescription = "Transaction Image",
                modifier = Modifier.padding(end = 18.dp)
            )
            Column {
                Text(
                    text = transaction.description,
                    style = Typography.h4,
                    color = BlueGrey700,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = transaction.transactionDate,
                    style = Typography.h5,
                    color = Gray
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            val amountColor = if(transaction.transactionType == "S") {
                BlueGrey700
            } else {
                Green
            }

            Text(
                text = "${transaction.amount} ${transaction.currencyCode}",
                style = Typography.h2,
                color = amountColor
            )

            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right arrow",
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun InvestingSafetySection() {
//    Card(
//        modifier = Modifier
//            .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
//        shape = RoundedCornerShape(10.dp),
//        elevation = 10.dp,
//        backgroundColor = BannerColor
//    )
    Surface(
        modifier = Modifier.padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        color = BannerColor
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Investing Safely",
                style = Typography.h3,
                color = Color.White
            )
            Text(
                text = "It's very difficult to time an investment especially when the market is volatile. Learn how to use dollar cost averaging to you advantage.",
                style = Typography.subtitle2,
                color = Color.White,
                lineHeight = 18.sp
            )
            Text(
                text = "Learn More",
                style = Typography.h3,
                color = Green,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {  }
            )
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
            .clickable {
                onCardClick(currency.currencyCode)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
        ) {
            CurrencyItem(currency = currency)
            ValuesItem(currency = currency)
        }
    }
}

@Composable
fun SetPriceAlertSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.notification_color),
                contentDescription = "Notification image in Price Alert section"
            )

            Column {

                Text(
                    text = "Set Price Alert",
                    style = Typography.h3
                )

                Text(
                    text = "Get notified when your coins are moving",
                    style = Typography.subtitle2,
                    color = Gray
                )
            }

            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right arrow"
            )
        }
    }
}

@Composable
fun CurrencyItem(currency: TrendingCurrency) {
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
fun ValuesItem(
    currency: TrendingCurrency
) {
    val changeColor by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                Green
            } else {
                Red
            }
        )
    }

    val changeOperator by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                "+"
            } else {
                "-"
            }
        )
    }

    Text(
        text = "??${currency.currentPrice}",
        style = Typography.h3,
        modifier = Modifier.padding(top = 20.dp)
    )

    Text(
        text = "$changeOperator${currency.changes}%",
        style = Typography.h3,
        modifier = Modifier.padding(top = 5.dp),
        color = changeColor
    )
}