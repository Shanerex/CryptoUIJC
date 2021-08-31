package com.example.cryptouijc.ui.Prices_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.models.TrendingCurrency
import com.example.cryptouijc.ui.theme.Gray
import com.example.cryptouijc.ui.theme.RobotoRegular
import com.example.cryptouijc.ui.theme.Typography

@Composable
fun CoinRateItem(
    currency: TrendingCurrency,
    onItemClick: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
            .clickable {
                onItemClick(currency.currencyCode)
            }
    ) {
        CurrencyInfoSection(currency = currency)
        
        CoinAmountSection(currency = currency)
    }
}

@Composable
fun CurrencyInfoSection(currency: TrendingCurrency) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = currency.imageRes),
            contentDescription = currency.currencyName,
            modifier = Modifier.padding(end = 18.dp)
        )
        Column {
            Text(
                text = currency.currencyName,
                style = Typography.h4,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = currency.currencyCode,
                style = Typography.h5,
                color = Gray
            )
        }
    }
}

@Composable
fun CoinAmountSection(currency: TrendingCurrency) {
    val operator by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                "+"
            } else {
                "-"
            }
        )
    }
    val changesColor by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                Color.Green
            } else {
                Color.Red
            }
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "£${currency.currentPrice}",
                style = TextStyle(
                    fontFamily = RobotoRegular,
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                ),
                color = Color.Black
            )
            Text(
                text = "$operator${currency.changes}",
                style = TextStyle(
                    fontFamily = RobotoRegular,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                ),
                color = changesColor
            )
        }
    }
}