package com.example.cryptouijc.ui

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.R
import com.example.cryptouijc.models.TrendingCurrency
import com.example.cryptouijc.ui.theme.*
import com.example.cryptouijc.utils.SampleData

@Composable
fun CryptoDetailsScreen(
    currencyCode: String,
    onBackArrowPressed: () -> Unit,
    onButtonClick: (String) -> Unit
) {
    val currency = SampleData.trendingCurrencies.find {
        it.currencyCode == currencyCode
    }!!

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightGray1
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 50.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopRow(
                onBackArrowPressed = onBackArrowPressed,
                isStarred = true
            )
            LineChartSection(currency = currency)
            BuyCryptoSection(
                currency = currency,
                onButtonClick = onButtonClick
            )
            CurrencyDescription(currency = currency)
            SetPriceAlertSection()
        }
    }
}

@Composable
fun CurrencyDescription(currency: TrendingCurrency) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "About ${currency.currencyName}",
                style = Typography.h2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currency.description,
                style = Typography.subtitle2,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun BuyCryptoSection(
    currency: TrendingCurrency,
    onButtonClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 10.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                CurrencyItem(currency = currency)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(horizontalAlignment = Alignment.End) {

                        Text(
                            text = "${SampleData.portfolio.balance}",
                            style = Typography.h2
                        )

                        Text(
                            text = "${currency.wallet.crypto} ${currency.currencyCode}",
                            style = Typography.subtitle2,
                            color = BlueGrey700
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "Right arrow",
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            StandardButton(
                onButtonClick = onButtonClick,
                currency = currency,
                buttonText = "Buy"
            )
        }
    }
}

@Composable
fun StandardButton(
    onButtonClick: (String) -> Unit,
    currency: TrendingCurrency,
    buttonText: String
) {
    Button(
        onClick = {
            onButtonClick(currency.currencyCode)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Green
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

@Composable
fun LineChartSection(currency: TrendingCurrency) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    CurrencyItem(currency = currency)

                    Column(modifier = Modifier.padding(end = 28.dp)) {
                        ValuesItem(currency = currency)
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.sample_line_chart_image),
                contentDescription = "Chart",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun TopRow(
    onBackArrowPressed: () -> Unit,
    isStarred: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 20.dp,
                end = 8.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back button",
                tint = Gray,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onBackArrowPressed()
                    }
            )

            Text(
                text = "Back",
                modifier = Modifier.padding(start = 4.dp),
                style = Typography.h2
            )
        }

        if(isStarred) {
            Row {
               Image(
                   painter = painterResource(id = R.drawable.star),
                   contentDescription = "Favourites Star",
                   modifier = Modifier.size(25.dp)
               )
            }
        }
    }
}