package com.example.cryptouijc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.ui.Trade_components.LimitSection
import com.example.cryptouijc.ui.Trade_components.RepeatSection
import com.example.cryptouijc.ui.Trade_components.TransactSection
import com.example.cryptouijc.ui.theme.BannerColor
import com.example.cryptouijc.ui.theme.RobotoBold
import com.example.cryptouijc.utils.SampleData

@Composable
fun TradeScreen(onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val selectedIndex = mutableStateOf(0)

        selectedIndex.value = tradeOptionsSection()

        when(selectedIndex.value) {
            0 -> TransactSection()
            1 -> RepeatSection()
            2 -> LimitSection()
        }

        Button(
            onClick = {
                onButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.textButtonColors(backgroundColor = BannerColor),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = "Trade",
                color = Color.White
            )
        }
    }
}

@Composable
private fun tradeOptionsSection(): Int {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SampleData.tradeScreenSections.forEachIndexed { index, sectionName ->
            Box(
                modifier = Modifier
                    .weight(1f)
//                    .padding(start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .clickable {
                        selectedIndex = index
                    }
                    .background(
                        if (selectedIndex == index) {
                            BannerColor
                        } else {
                            Color.White
                        }
                    )
            ) {
                Text(
                    text = sectionName,
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .align(Alignment.Center),
                    color = if(selectedIndex == index) {
                        Color.White
                    } else {
                        Color.Black
                    },
                    fontSize = 14.sp,
                    fontFamily = RobotoBold
                )
            }
        }
    }

    return selectedIndex
}