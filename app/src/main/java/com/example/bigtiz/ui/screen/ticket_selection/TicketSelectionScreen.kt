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
import com.example.bigtiz.ui.common.HamburgerMenuButton
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

var money by mutableIntStateOf(10000)

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
fun TicketSelectionScreen(tickets: Tickets, file: File) {
    DrawSurface()
    ColumnsOfOvals(tickets, file)
}

@Composable
private fun ColumnsOfOvals(tickets: Tickets, file: File) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy((-15).dp)) {
            DrawUpperOval()
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
private fun DrawUpperOval() {
    val list = listOf(Color.Gray, Color.Gray, Color.White)
    Box(
        modifier = Modifier
            .padding(vertical = 25.dp)
            .padding(start = 5.dp)
            .padding(end = 5.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.Gray.copy(alpha = 0.5f))
            .padding(horizontal = 20.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.ticket_selection_bigtiz),
                fontSize = 25.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(list),
                ),
            )
        }
        DrawHamburgerMenu()
    }
}

@Composable
private fun DrawHamburgerMenu() {
    HamburgerMenuButton(
        onClick = { print("Тут окно 1") },
    )
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
                modifier = Modifier
                    .height(30.dp)
                    .padding(vertical = 2.dp),
                fontWeight = FontWeight.Bold,
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


