package com.example.bigtiz.ui.screen.pilot_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.R
import com.example.bigtiz.Racer
import com.example.bigtiz.RacersData
import com.example.bigtiz.ui.common.HamburgerMenuButton
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable

val gradientList = listOf(Color.Gray, Color.Gray, Color.White)

@Composable
fun PilotDetailsScreen(
    balanceRub: Int = 1450,
    onBuyTicketClick: () -> Unit = {},
) {

    var isMenuVisible by remember { mutableStateOf(false) }

    var currentRacer by remember { mutableStateOf(RacersData.diana) }

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
            PilotDetailsTopBar(
                title = "Бик Тиз",
                balanceRub = balanceRub,
                onMenuClick = {
                    isMenuVisible = true
                },
            )

            DrawPhotoAndDescription(racer = currentRacer)
        }

        if (isMenuVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { isMenuVisible = false }
            ) {
                DrawMenu(onClose = { isMenuVisible = false })
            }
        }
    }
}

@Composable
private fun PilotDetailsTopBar(
    title: String,
    balanceRub: Int,
    onMenuClick: () -> Unit,
) {
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
            text = title,
            fontSize = 24.sp,
            style = TextStyle(brush = Brush.linearGradient(gradientList)),
            modifier = Modifier.align(Alignment.Center),
        )

        HamburgerMenuButton(
            onClick = onMenuClick,
            modifier = Modifier.align(Alignment.CenterStart),
        )

        BalanceChip(
            balanceRub = balanceRub,
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}

@Composable
private fun BalanceChip(
    balanceRub: Int,
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
            text = balanceRub.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun DrawPhotoAndDescription(racer: Racer) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = racer.painter,
                contentDescription = "Фотография ${racer.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.DarkGray.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${racer.name}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = TextStyle(
                            brush = Brush.linearGradient(gradientList)
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "${racer.fullDescription}",
                        fontSize = 18.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}


@Composable
fun DrawMenu(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(0.6f),
            )
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(start = 20.dp, top = 90.dp),
    ) {
        Box(
            modifier = Modifier
                .height(750.dp)
                .width(175.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {

            Image(
                painter = painterResource(id = R.drawable.wp1),
                contentDescription = "фон навигационного меню",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .size(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.navigation_menu_diana),
                        contentDescription = "диана",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {println("переход на биографию аниты")},
                    modifier = Modifier
                        .size(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.navigation_menu_anita),
                        contentDescription = "анита",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {println("переход на биографию алины")},
                    modifier = Modifier
                        .size(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.navigation_menu_alina),
                        contentDescription = "алина",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {println("переход на биографию александра")},
                    modifier = Modifier
                        .size(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.navigation_menu_alexander),
                        contentDescription = "александр",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { print("переход на главную страницу") },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .width(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray.copy(alpha = 0.2f),
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.navigation_menu_home),
                            contentDescription = "кнопка на главную",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(40.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = "вернуться на главную",
                            fontSize = 13.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.LightGray,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}



