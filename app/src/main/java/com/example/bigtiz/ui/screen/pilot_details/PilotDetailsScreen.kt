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
import androidx.compose.ui.text.font.FontStyle
import com.example.bigtiz.ui.common.Header

val gradientList = listOf(Color.Gray, Color.Gray, Color.White)

@Composable
fun PilotDetailsScreen(
    currentRacer: Racer = RacersData.diana
) {

    var selectedRacer by remember { mutableStateOf(currentRacer) }
    var isMenuVisible by remember { mutableStateOf(false) }

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
                onMenuClick = {
                    isMenuVisible = true
                },
            )

            PhotoAndDescription(racer = selectedRacer)
        }

        if (isMenuVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { isMenuVisible = false }
            ) {
                NavigationMenu(
                    onClose = { isMenuVisible = false },
                    onRacerClick = { racer ->
                        selectedRacer = racer
                        isMenuVisible = false
                    }
                )
            }
        }
    }
}

@Composable
private fun RacerBio(racer: Racer) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = racer.fullName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = racer.country,
                fontSize = 16.sp,
                color = Color.Red.copy(alpha = 0.7f)
            )
            Text(
                text = "•",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.5f)
            )
            Text(
                text = "${racer.age} лет",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = "•",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.5f)
            )
            Text(
                text = "${racer.wins} подиумов",
                fontSize = 16.sp,
                color = Color.Green
            )
        }

        Text(
            text = "«${racer.quote}»",
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = racer.bio,
            fontSize = 16.sp,
            color = Color.White,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun PilotDetailsTopBar(
    onMenuClick: () -> Unit,
) {
    Header(onMenuClick)
}


@Composable
private fun PhotoAndDescription(racer: Racer) {
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
            RacerImage(racer)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wp1),
                    contentDescription = "фон текста биографии",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "${racer.name}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = TextStyle(
                            brush = Brush.linearGradient(gradientList)
                        ),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    RacerBio(racer)
                }
            }
        }
    }
}

@Composable
private fun RacerImage(racer: Racer) {
    Image(
        painter = painterResource(id = racer.imageResId),
        contentDescription = racer.name,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
@Composable
private fun ScreenDimming() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(0.6f),
            )
    )
}

@Composable
private fun PhotoAsButton(
    racer: Racer,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(160.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        RacerImage(racer)
    }
}
@Composable
private fun ButtonGoHome() {
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

@Composable
private fun NavigationMenu(
    onClose: () -> Unit,
    onRacerClick: (Racer) -> Unit
) {
    ScreenDimming()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 80.dp),
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            ) {

                Spacer(modifier = Modifier.height(5.dp))

                for (racer in RacersData.allRacers) {
                    PhotoAsButton(
                        racer = racer,
                        onClick = { onRacerClick(racer) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                ButtonGoHome()
            }
        }
    }
}






