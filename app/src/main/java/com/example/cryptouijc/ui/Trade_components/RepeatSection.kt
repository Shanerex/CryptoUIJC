package com.example.cryptouijc.ui.Trade_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.cryptouijc.ui.theme.LightGray1
import com.example.cryptouijc.utils.SampleData

@Composable
fun RepeatSection() {
    TransactSection()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = LightGray1)
        ) {
            SampleData.repeatOptions.forEachIndexed { index, repeatOption ->
                RepeatOptionsItem(
                    iconImageVector = repeatOption.iconImageVector,
                    optionName = repeatOption.optionName,
                    frequency = repeatOption.frequency
                )
            }
        }
    }
}