package com.example.bigtiz.ui.screen.ticket_selection

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun ColumnsOfOvals(tickets: Tickets, file: File) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy((-15).dp)) {
            UpperOval().DrawUpperOval()
            OvalBelowUpper().DrawOvalBelowUpper()
        }
        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            FanZone(tickets).DrawFanZone()
            VipZone(tickets).DrawVipZone()
            PremiumZone(tickets).DrawPremiumZone()
        }

        TotalMenu().DrawTotal()
        PayButton().DrawPayButton(tickets, file)
    }
}

@Composable
fun DrawSurface() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

class UpperOval {
    @Composable
    fun DrawUpperOval() {
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
                    text = "Бик Тиз",
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
    fun DrawHamburgerMenu() {
        HamburgerMenuButton(
            onClick = { print("Тут окно 1") }, // ....
        )
        MoneyIcon()
    }

    @Composable
    fun MoneyIcon() {
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
}

class OvalBelowUpper {
    @Composable
    fun DrawOvalBelowUpper() {
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
}

class FanZone(private val tickets: Tickets) {
    companion object {
        var value by mutableIntStateOf(0)
    }

    @Composable
    fun DrawFanZone() {
        var amountOfFanZone by remember { mutableStateOf(tickets.FanZone) }
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
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.size(160.dp, 100.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy((-15).dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(vertical = 25.dp),
                            text = "Fan Zone",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "${amountOfFanZone} tickets left",
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontSize = 20.sp,
                            color = Color.Red,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp, 60.dp)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.Gray)
                                .border(3.dp, Color.White, RoundedCornerShape(50)),
                        ) {
                            if (FanZonePrice.toString().length == 4) {
                                Text(
                                    text = "$FanZonePrice₽",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                        .padding(horizontal = 10.dp),
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                )
                            } else {
                                Text(
                                    text = "$FanZonePrice₽",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                        .padding(horizontal = 16.dp),
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                )
                            }
                        }
                        Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(100))) {
                            Button(
                                onClick = {
                                    if (value != 0) {
                                        value -= 1
                                        amountOfFanZone += 1
                                        Total -= FanZonePrice
                                    }
                                },
                                enabled = true,
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(45.dp)
                                .clip(RoundedCornerShape(100))
                                .background(Color.Gray),
                        ) {
                            if (value - 1 >= 0 || value == 0) {
                                when (value) {
                                    in 0..9 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    in 10..99 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    in 100..999 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                }
                            }
                        }

                        Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(100))) {
                            Button(
                                onClick = {
                                    if (value < tickets.FanZone) {
                                        value += 1
                                        amountOfFanZone -= 1
                                        Total += FanZonePrice
                                    }
                                },
                                enabled = true,
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class VipZone(private val tickets: Tickets) {
    companion object {
        var value by mutableIntStateOf(0)
    }

    @Composable
    fun DrawVipZone() {
        var amountOfVipZone by remember { mutableStateOf(tickets.VipZone) }
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
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.size(160.dp, 100.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy((-15).dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(vertical = 25.dp),
                            text = "Vip Zone",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "${amountOfVipZone} tickets left",
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontSize = 20.sp,
                            color = Color.Red,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp, 60.dp)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.Gray)
                                .border(3.dp, Color.White, RoundedCornerShape(50)),
                        ) {
                            if (VipZonePrice.toString().length == 4) {
                                Text(
                                    text = "$VipZonePrice₽",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                        .padding(horizontal = 10.dp),
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                )
                            } else {
                                Text(
                                    text = "$VipZonePrice₽",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                        .padding(horizontal = 19.dp),
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                )
                            }
                        }

                        Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(100))) {
                            Button(
                                onClick = {
                                    if (value != 0) {
                                        value -= 1
                                        amountOfVipZone += 1
                                        Total -= VipZonePrice
                                    }
                                },
                                enabled = true,
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(45.dp)
                                .clip(RoundedCornerShape(100))
                                .background(Color.Gray),
                        ) {
                            if (value - 1 >= 0 || value == 0) {
                                when (value) {
                                    in 0..9 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    in 10..99 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    in 100..999 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                }
                            }
                        }

                        Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(100))) {
                            Button(
                                onClick = {
                                    if (value < tickets.VipZone) {
                                        Total += VipZonePrice
                                        value += 1
                                        amountOfVipZone -= 1
                                    }
                                },
                                enabled = true,
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class PremiumZone(private val tickets: Tickets) {
    companion object {
        var valuePrem by mutableIntStateOf(0)
    }

    @Composable
    fun DrawPremiumZone() {
        var amountOfPremuimZone by remember { mutableStateOf(tickets.PremiumZone) }
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
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.size(160.dp, 100.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-20).dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 19.dp)
                                .padding(vertical = 19.dp),
                            text = "Premium",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 40.dp),
                            text = "Zone",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = "${amountOfPremuimZone} tickets left",
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontSize = 20.sp,
                            color = Color.Red,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp, 60.dp)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.Gray)
                                .border(3.dp, Color.White, RoundedCornerShape(50)),
                        ) {
                            if (PremuimZonePrice.toString().length == 4) {
                                Text(
                                    text = "$PremuimZonePrice₽",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                        .padding(horizontal = 10.dp),
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                )
                            } else {
                                Text(
                                    text = "$PremuimZonePrice₽",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                        .padding(horizontal = 19.dp),
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                )
                            }
                        }

                        Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(100))) {
                            Button(
                                onClick = {
                                    if (valuePrem != 0) {
                                        Total -= PremuimZonePrice
                                        valuePrem -= 1
                                        amountOfPremuimZone += 1
                                    }
                                },
                                enabled = true,
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .height(45.dp)
                                .width(50.dp)
                                .clip(RoundedCornerShape(100))
                                .background(Color.Gray),
                        ) {
                            if (valuePrem - 1 >= 0 || valuePrem == 0) {
                                when (valuePrem) {
                                    in 0..9 ->
                                        Text(
                                            text = "$valuePrem",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    in 10..99 ->
                                        Text(
                                            text = "$valuePrem",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                    in 100..999 ->
                                        Text(
                                            text = "$valuePrem",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                        )
                                }
                            }
                        }

                        Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(100))) {
                            Button(
                                onClick = {
                                    if (valuePrem < tickets.PremiumZone) {
                                        Total += PremuimZonePrice
                                        valuePrem += 1
                                        amountOfPremuimZone -= 1
                                    }
                                },
                                enabled = true,
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class PayButton {
    @Composable
    fun DrawPayButton(tickets: Tickets, file: File) {
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
                            tickets.PremiumZone -= PremiumZone.valuePrem
                            tickets.VipZone -= VipZone.value
                            tickets.FanZone -= FanZone.value

                            val newjson = Json.encodeToString(tickets)
                            file.writeText(newjson)

                            FanZone.value = 0
                            VipZone.value = 0
                            PremiumZone.valuePrem = 0
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
}

class TotalMenu {
    @Composable
    fun DrawTotal() {
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
}

