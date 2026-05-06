package com.example.bigtiz.ui.screen.ticket_selection

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.R
import com.example.bigtiz.domain.model.TicketCategory
import com.example.bigtiz.presentation.model.TicketSelectionUiModel
import com.example.bigtiz.ui.common.Header
import com.example.bigtiz.ui.common.AppConstants
import com.example.bigtiz.ui.common.HamburgerMenuButton
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

var money by mutableIntStateOf(AppConstants.balance)

const val FanZonePrice: Int = 100
const val VipZonePrice: Int = 1400
const val PremuimZonePrice: Int = 2500

var Total by mutableIntStateOf(0)
var valuePrem by mutableIntStateOf(0)
var valueFan by mutableIntStateOf(0)

var valueVip by mutableIntStateOf(0)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Tickets(var FanZone: Int, var PremiumZone: Int, var VipZone: Int)

val jsonConfig: Json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

@Composable
fun TicketSelectionScreen(tickets: Tickets, file: File, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        DrawSurface()
        ColumnsOfOvals(tickets, file, onClick)
    }
}

@Composable
fun TicketSelectionScreen(
    uiModel: TicketSelectionUiModel,
    onMenuClick: () -> Unit = {},
    onDecrease: (TicketCategory) -> Unit = {},
    onIncrease: (TicketCategory) -> Unit = {},
    onPurchase: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        DrawSurface()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Header(onMenuClick)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF1C1C1C).copy(alpha = 0.55f))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = uiModel.raceTitle,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = uiModel.raceLocation,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF1C1C1C).copy(alpha = 0.55f))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                uiModel.zones.forEach { zone ->
                    DrawZone(
                        title = zone.title,
                        ticketsLeftInitial = zone.ticketsLeft,
                        price = zone.priceRub,
                        selectedValue = zone.selectedCount,
                        onIncrease = { onIncrease(zone.category) },
                        onDecrease = { onDecrease(zone.category) },
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF1C1C1C).copy(alpha = 0.55f))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "Баланс",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "${uiModel.balanceRub} ₽",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            text = "Итого",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "${uiModel.totalRub} ₽",
                            color = Color(0xFFA9FF9A),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        )
                    }
                }
            }

            Button(
                onClick = onPurchase,
                enabled = uiModel.canPurchase,
                shape = RoundedCornerShape(18.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6B6B6B).copy(alpha = 0.7f),
                    contentColor = Color(0xFFE0E0E0),
                    disabledContainerColor = Color(0xFF3A3A3A).copy(alpha = 0.7f),
                    disabledContentColor = Color(0xFF9B9B9B),
                ),
            ) {
                Text(
                    text = "Purchase",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun ColumnsOfOvals(tickets: Tickets, file: File, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy((10).dp)) {
            DrawUpperOval(onClick)
            DrawOvalBelowUpper()
        }
        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            DrawZone(
                title = "Fan Zone",
                ticketsLeftInitial = tickets.FanZone,
                price = FanZonePrice,
                selectedValue = valueFan,
                onIncrease = {
                    valueFan++
                    Total += FanZonePrice
                },
                onDecrease = {
                    valueFan--
                    Total -= FanZonePrice
                })

            DrawZone(
                title = "Vip Zone",
                ticketsLeftInitial = tickets.VipZone,
                price = VipZonePrice,
                selectedValue = valueVip,
                onIncrease = {
                    valueVip++
                    Total += VipZonePrice
                },
                onDecrease = {
                    valueVip--
                    Total -= VipZonePrice
                })

            DrawZone(
                title = "Premium Zone",
                ticketsLeftInitial = tickets.PremiumZone,
                price = PremuimZonePrice,
                selectedValue = valuePrem,
                onIncrease = {
                    valuePrem++
                    Total += PremuimZonePrice
                },
                onDecrease = {
                    valuePrem--
                    Total -= PremuimZonePrice
                })
        }

        DrawTotal()
        DrawPayButton(tickets, file)
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
private fun DrawUpperOval(onClick: () -> Unit) {

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
            DrawHamburgerMenu(onClick)

        }

}


@Composable
private fun DrawHamburgerMenu(onClick : () -> Unit) {
    HamburgerMenuButton(onClick)
    MoneyIcon()
}

@Composable
private fun MoneyIcon() {
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
                contentDescription = "Создает рубль",
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .size(20.dp)
                    .background(Color.Gray),
            )
            Text(
                text = "$money",
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
    ticketsLeftInitial: Int,
    price: Int,
    selectedValue: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    var ticketsLeft by remember { mutableStateOf(ticketsLeftInitial) }

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

            Box(
                modifier = Modifier.size(160.dp, 100.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
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
                    onClick = {
                        if (selectedValue > 0) {
                            ticketsLeft++
                            onDecrease()
                        }
                    },
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
                        .background(Color.Gray), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$selectedValue",
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        if (ticketsLeft > 0) {
                            ticketsLeft--
                            onIncrease()
                        }
                    },
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
private fun DrawPayButton(tickets: Tickets, file: File) {
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
                onClick = {
                    if (Total <= money) {
                        money -= Total
                        AppConstants.balance -= Total
                        tickets.PremiumZone -= valuePrem
                        tickets.VipZone -= valueVip
                        tickets.FanZone -= valueFan

                        val newjson = Json.encodeToString(tickets)
                        file.writeText(newjson)

                        valueFan = 0
                        valueVip = 0
                        valuePrem = 0
                        Total = 0
                    }
                },
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.fillMaxSize(),
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


@Composable
private fun DrawTotal() {
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
                text = "Total: $Total ₽ ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


