package com.example.bigtiz.ui.screen.schedule_of_races

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.R
import com.example.bigtiz.ui.common.HamburgerMenuButton

@Composable
fun ScheduleOfRacesScreen(
    balance: Int = 1450,
    onMenuClick: () -> Unit = {},
    onTicketClick: () -> Unit = {},
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ScheduleHeader(
                balance = balance,
                onMenuClick = onMenuClick,
            )
            CityRace(
                dayMonth = "04.03",
                year = "2026",
                city = "Австралия",
                onTicketClick = onTicketClick
            )
            CityRace(
                dayMonth = "05.04",
                year = "2026",
                city = "Германия",
                onTicketClick = onTicketClick
            )
            CityRace(
                dayMonth = "04.05",
                year = "2026",
                city = "Турция",
                onTicketClick = onTicketClick
            )
        }
    }
}

@Composable
private fun ScheduleHeader(
    balance: Int,
    onMenuClick: () -> Unit
) {
    val list = listOf(Color.Gray, Color.Gray, Color.White)

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

@Composable
private fun CityRace(
    dayMonth: String,
    year: String,
    city: String,
    onTicketClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F0F0F).copy(alpha = 0.5f)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = dayMonth,
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = year,
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = city,
                fontSize = 34.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            TicketButton(onClick = onTicketClick)
        }
    }
}

@Composable
private fun TicketButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(90.dp, 50.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.2f)
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "БИЛЕТ",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.9f),
            fontWeight = FontWeight.Medium,
        )
    }
}