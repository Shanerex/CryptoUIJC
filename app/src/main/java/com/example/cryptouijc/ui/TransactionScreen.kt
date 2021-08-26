package com.example.cryptouijc.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptouijc.models.TrendingCurrency
import com.example.cryptouijc.ui.theme.BlueGrey700
import com.example.cryptouijc.ui.theme.LightGray1
import com.example.cryptouijc.ui.theme.Typography
import com.example.cryptouijc.utils.SampleData

@Composable
fun TransactionScreen(
    onBackArrowPressed: () -> Unit,
    currencyCode: String,
    onTradeButtonClick: (String) -> Unit
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
                onBackArrowPressed,
                false
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    CurrencyItem(currency = currency)

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "%.3f ${currency.currencyCode}".format(currency.wallet.crypto),
                            style = Typography.h2
                        )
                        Text(
                            text = "£${SampleData.portfolio.balance}",
                            style = Typography.subtitle2,
                            color = BlueGrey700
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    StandardButton(
                        onButtonClick = onTradeButtonClick,
                        currency = currency,
                        buttonText = "Trade"
                    )
                }
            }

            TransactionHistorySection()
        }
    }
}