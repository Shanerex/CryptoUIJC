package com.example.cryptouijc.ui.Trade_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.ui.Prices_components.CurrencyInfoSection
import com.example.cryptouijc.ui.theme.Gray
import com.example.cryptouijc.ui.theme.RobotoRegular
import com.example.cryptouijc.utils.SampleData

@Composable
fun TransactSection() {
    TradeTextField(label = "From")

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {

        CurrencySelectorDropDown(selectedIndex = 0)

        CurrencySelectorDropDown(selectedIndex = 1)
    }

    TradeTextField(label = "To")
}

@Composable
private fun CurrencySelectorDropDown(selectedIndex: Int) {
    var expandedState by remember {
        mutableStateOf(false)
    }

    var dropDownSelectedIndex by remember {
        mutableStateOf(selectedIndex)
    }

    Box {
        Box(
            modifier = Modifier
                .width(150.dp)
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    expandedState = true
                }
                .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(5.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CurrencyInfoSection(currency = SampleData.trendingCurrencies[dropDownSelectedIndex])
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "See more coins",
                    tint = Gray
                )
            }
        }
        DropdownMenu(
            expanded = expandedState,
            onDismissRequest = {
                expandedState = false
            },
            modifier = Modifier
                .width(150.dp)
                .border(1.dp, color = Gray, shape = RoundedCornerShape(4.dp))
        ) {
            SampleData.trendingCurrencies.forEachIndexed { index, currency ->
                DropdownMenuItem(
                    onClick = {
                        dropDownSelectedIndex = index
                        expandedState = false
                    }
                ) {
                    CurrencyInfoSection(currency = currency)
                }
            }
        }
    }
}

@Composable
private fun TradeTextField(label: String) {
    var textFieldState by remember {
        mutableStateOf(TextFieldValue("0"))
    }

    OutlinedTextField(
        value = textFieldState,
        onValueChange = {
            textFieldState = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
        label = {
            Text(text = label)
        },
        textStyle = TextStyle(
            fontFamily = RobotoRegular,
            fontSize = 14.sp,
            lineHeight = 22.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
    )
}