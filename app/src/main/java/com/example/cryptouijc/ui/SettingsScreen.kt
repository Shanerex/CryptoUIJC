package com.example.cryptouijc.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptouijc.utils.SampleData
import com.example.cryptouijc.R
import com.example.cryptouijc.ui.theme.*

@Composable
fun SettingsScreen(
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
            Spacer(modifier = Modifier.height(12.dp))

            UserInfo()

            SettingsSection()
        }
    }
}

@Composable
private fun SettingsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        SettingsItem(
            iconImageVector = Icons.Default.Person,
            text = "Profile"
        )
        SettingsItem(
            iconImageVector = Icons.Default.Tune,
            text = "Preferences"
        )
        SettingsItem(
            iconImageVector = Icons.Default.Notifications,
            text = "Notifications"
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )
        SettingsItem(
            iconImageVector = Icons.Default.Security,
            text = "Security"
        )
        SettingsItem(
            iconImageVector = Icons.Default.Lock,
            text = "Privacy"
        )
        SettingsItem(
            iconImageVector = Icons.Default.Info,
            text = "About"
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )
        SettingsItem(
            iconImageVector = Icons.Default.Logout,
            text = "Log Out"
        )
    }
}

@Composable
private fun SettingsItem(
    iconImageVector: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Icon(
                imageVector = iconImageVector,
                contentDescription = text,
                tint = BannerColor,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = RobotoRegular,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = "Right Arrow",
            modifier = Modifier
                .size(12.dp)
                .clipToBounds()
        )
    }
}

@Composable
private fun UserInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${SampleData.user.firstName} ${SampleData.user.lastName}",
            style = Typography.h2,
            color = Color.Black
        )
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Icon",
            tint = Gray,
            modifier = Modifier.size(48.dp)
        )
    }
}