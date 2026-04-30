package com.example.bigtiz.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.R

@Composable
fun Header(
    onMenuClick: () -> Unit
) {
    var balance by remember { mutableStateOf(AppConstants.balance) }
    val list = remember { mutableListOf(Color.Gray, Color.Gray, Color.White)}
    Box(
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF1C1C1C).copy(alpha = 0.75f))
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Бик Тиз",
            fontSize = 24.sp,
            style = TextStyle(brush = Brush.linearGradient(list)),
            modifier = Modifier.align(Alignment.Center),
        )

        HamburgerMenuButton(
            onClick = onMenuClick,
            modifier = Modifier.align(Alignment.CenterStart),
        )

        Balance(
            balance = balance,
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}

@Composable
private fun Balance(
    balance: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(R.drawable.rubblik),
            contentDescription = "Рубли",
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .size(20.dp)
                .background(Color.Gray),
            tint = Color.Unspecified,
        )
        Text(
            text = balance.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}