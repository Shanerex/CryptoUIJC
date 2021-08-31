package com.example.cryptouijc.ui.Trade_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.ui.theme.Gray
import com.example.cryptouijc.ui.theme.RobotoRegular

@Composable
fun RepeatOptionsItem(
    iconImageVector: ImageVector,
    optionName: String,
    frequency: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = iconImageVector,
                contentDescription = optionName,
                tint = Gray
            )
            Text(
                text = optionName,
                fontFamily = RobotoRegular,
                fontSize = 14.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = frequency,
                fontFamily = RobotoRegular,
                fontSize = 14.sp
            )
            Icon(
                imageVector = Icons.Default.NavigateNext,
                contentDescription = "Next",
                tint = Gray
            )
        }
    }
}