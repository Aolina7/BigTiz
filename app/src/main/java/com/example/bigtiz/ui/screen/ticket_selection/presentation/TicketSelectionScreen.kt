package com.example.bigtiz.ui.screen.ticket_selection.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.R
import com.example.bigtiz.ui.common.HamburgerMenuButton


@Composable
fun TicketSelectionScreen(
    viewModel: TicketViewModel,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        DrawSurface()
        ColumnsOfOvals(viewModel, onClick)
    }
}


@Composable
private fun ColumnsOfOvals(
    viewModel: TicketViewModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            DrawUpperOval(viewModel, onClick)
            DrawOvalBelowUpper()
        }

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {

            DrawZone(
                title = "Fan Zone",
                ticketsLeft = viewModel.tickets.FanZone,
                price = viewModel.getFanPrice(),
                selectedValue = viewModel.uiState.fanSelected,
                onIncrease = { viewModel.increaseFan() },
                onDecrease = { viewModel.decreaseFan() }
            )

            DrawZone(
                title = "Vip Zone",
                ticketsLeft = viewModel.tickets.VipZone,
                price = viewModel.getVipPrice(),
                selectedValue = viewModel.uiState.vipSelected,
                onIncrease = { viewModel.increaseVip() },
                onDecrease = { viewModel.decreaseVip() }
            )

            DrawZone(
                title = "Premium Zone",
                ticketsLeft = viewModel.tickets.PremiumZone,
                price = viewModel.getPremPrice(),
                selectedValue = viewModel.uiState.premSelected,
                onIncrease = { viewModel.increasePrem() },
                onDecrease = { viewModel.decreasePrem() }
            )
        }

        DrawTotal(viewModel)
        DrawPayButton(viewModel)
    }
}


@Composable
private fun DrawSurface() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
private fun DrawUpperOval(viewModel: TicketViewModel, onClick: () -> Unit) {

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
        DrawHamburgerMenu(viewModel, onClick)
    }
}

@Composable
private fun DrawHamburgerMenu(viewModel: TicketViewModel, onClick: () -> Unit) {
    HamburgerMenuButton(onClick)
    MoneyIcon(viewModel)
}

@Composable
private fun MoneyIcon(viewModel: TicketViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 60.dp)
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.rubblik),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .size(20.dp)
                    .background(Color.Gray),
            )
            Text(
                text = "${viewModel.uiState.money}",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}



@Composable
private fun DrawOvalBelowUpper() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.Gray.copy(alpha = 0.5f)),
    ) {
        Text(
            text = "Australia???",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 20.dp),
        )
    }
}


@Composable
fun DrawZone(
    title: String,
    ticketsLeft: Int,
    price: Int,
    selectedValue: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(20))
            .background(Color.Gray.copy(alpha = 0.5f)),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy((-10).dp),
        ) {

            Column(
                modifier = Modifier
                    .width(160.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "$ticketsLeft tickets left",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontSize = 20.sp,
                    color = Color.Red,
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {

                Box(
                    modifier = Modifier
                        .size(90.dp, 60.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Gray)
                        .border(3.dp, Color.White, RoundedCornerShape(50)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$price₽",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Button(
                    onClick = onDecrease,
                    modifier = Modifier.size(25.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Text("-", fontWeight = FontWeight.Bold)
                }

                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(45.dp)
                        .clip(RoundedCornerShape(100))
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$selectedValue",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onIncrease,
                    modifier = Modifier.size(25.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Text("+", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
private fun DrawTotal(viewModel: TicketViewModel) {
    Box(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxHeight()
                .width(200.dp)
                .clip(RoundedCornerShape(20))
                .background(Color.Gray.copy(alpha = 0.7f)),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 5.dp),
                text = "Total: ${viewModel.total} ₽ ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
private fun DrawPayButton(viewModel: TicketViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(250.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(20))
                .background(Color.Magenta),
        ) {
            Button(
                onClick = { viewModel.purchase() },
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray),
            ) {
                Text(
                    text = "Purchase",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray,
                )
            }
        }
    }
}