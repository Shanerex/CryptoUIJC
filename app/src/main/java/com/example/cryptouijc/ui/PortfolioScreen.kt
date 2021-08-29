package com.example.cryptouijc.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import com.example.cryptouijc.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.models.TrendingCurrency
import com.example.cryptouijc.ui.theme.*
import com.example.cryptouijc.utils.SampleData

@ExperimentalComposeUiApi
@Composable
fun PortfolioScreen(
    onBackArrowPressed: () -> Unit
) {
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
                isStarred = false
            )
            PortfolioSection()
            SearchSection()
            CurrencySection()
        }
    }
}

@Composable
private fun CurrencySection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 8.dp, start = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp)
        ) {
            SampleData.trendingCurrencies.forEachIndexed { index, currency ->
                CoinItem(currency = currency)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                        start = 12.dp,
                        bottom = if(index < SampleData.trendingCurrencies.size - 1) {
                            12.dp
                        } else {
                            0.dp
                        }
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SampleData.trendingCurrencies.forEachIndexed { index, currency ->
                CoinItem(currency = currency)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                        start = 12.dp,
                        bottom = if(index < SampleData.trendingCurrencies.size - 1) {
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
private fun CoinItem(currency: TrendingCurrency) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${currency.wallet.crypto}",
                    style = TextStyle(
                        fontFamily = RobotoRegular,
                        fontSize = 16.sp,
                        lineHeight = 18.sp
                    ),
                    color = Color.DarkGray
                )
                Text(
                    text = "£${currency.wallet.value}",
                    style = TextStyle(
                        fontFamily = RobotoRegular,
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    ),
                    color = Gray
                )
            }
            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right Arrow",
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun SearchSection() {

    var textState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    OutlinedTextField(value = textState,
        onValueChange = {
            textState = it
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                modifier = Modifier
                    .padding(12.dp)
            )
        },
        trailingIcon = {
            if (textState != TextFieldValue("")) {
                IconButton(onClick = {
                    textState = TextFieldValue("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete text button",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        maxLines = 1,
        label = {
            Text(text = "Type the coin name you need")
        },
        textStyle = TextStyle(
            fontFamily = RobotoRegular,
            fontSize = 14.sp,
            lineHeight = 18.sp
        ),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
    )
}

@Composable
private fun PortfolioSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 8.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp, start = 12.dp, end = 12.dp, bottom = 16.dp)
        ) {
            Column() {
                Text(
                    text = "TOTAL PORTFOLIO VALUE",
                    style = Typography.subtitle1,
                    color = Gray
                )
                Text(
                    text = "£${SampleData.portfolio.balance}",
                    modifier = Modifier.padding(top = 12.dp),
                    style = TextStyle(
                        fontFamily = RobotoBold,
                        fontSize = 24.sp,
                        lineHeight = 30.sp,
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.crypto_icon),
                contentDescription = "Crypto Icon",
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
            )
        }
    }
}