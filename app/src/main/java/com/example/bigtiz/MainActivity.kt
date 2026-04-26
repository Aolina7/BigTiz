package com.example.bigtiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

var money by mutableStateOf(10000)
val FanZonePrice: Int = 100
val VipZonePrice: Int = 1400
val PremuimZonePrice: Int = 2500

var Total by mutableStateOf(0)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Tickets(var FanZone: Int, var PremiumZone: Int, var VipZone: Int)

val jsonConfig = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

//ctrl+alt+L - сделать красиво код
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataFile =
            File(filesDir, "DataBase.json") //todo лишнее . лучше просто в константе хранить данные
        if (!dataFile.exists()) {
            assets.open("DataBase.json").use { input ->
                dataFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }

        val jsonString = dataFile.readText()
        val ticket = jsonConfig.decodeFromString<Tickets>(jsonString)
        var dictionary = mutableMapOf<String, Int>() //todo unused

        enableEdgeToEdge()
        setContent {
            DrawSurface()
            ColumnsOfOvals(ticket, dataFile)
        }
    }
}

@Composable
fun ColumnsOfOvals(
    tickets: Tickets,
    file: File
) { //todo если ничего не возвращается, тип не пишется
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy((-15).dp)) { //todo отрицательный отступ?? + отступы должны соответствовать MaterialDesign (т.е делиться на 4) https://m2.material.io/design/layout/spacing-methods.html#baseline-grid
            UpperOval().DrawUpperOval()
            OvalBelowUpper().DrawOvalBelowUpper()
        }
        Spacer(
            Modifier.height(20.dp)
        )

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement
                .spacedBy(15.dp)
        ) {
            FanZone(tickets).DrawFanZone()
            VipZone(tickets).DrawVipZone()
            PremiumZone(tickets).DrawPremiumZone()
        }

        TotalMenu().DrawTotal()

        PayButton().DrawPayButton(tickets, file)
    }
}

// Данная функция создает поверхность и накладывает на нее фото
@Composable
fun DrawSurface(): Unit {
    Surface(
        modifier = Modifier.fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = "",
            contentScale = ContentScale.Crop //todo добавить импорт
        )
    }
}

// Данная функция создает овал вверху с текстом "Бик Тиз"

//todo отдельные классы для composable не пишутся - compose про функции
class UpperOval {
    @Composable
    fun DrawUpperOval(): Unit {
        val list = listOf(Color.Gray, Color.Gray, Color.White)
        Box(
            modifier = Modifier
                .padding(vertical = 25.dp) //todo есть другой конструктор с 4мя параметрами на вход на каждую сторону
                .padding(start = 5.dp)
                .padding(end = 5.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Gray.copy(alpha = 0.5f))
                .padding(horizontal = 20.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center, //todo лишний импорт
            ) {
                Text(
                    text = "Бик Тиз",
                    fontSize = 25.sp,
                    style = TextStyle(
                        brush = Brush.linearGradient(list)
                    )

                )
            }
            DrawHamburgerMenu()

        }
    }

    // Три полоски сверху на овале
    @Composable
    fun DrawHamburgerMenu(): Unit {
        Box(
            modifier = Modifier.size(30.dp, 56.dp)
        ) {
            IconButton(
                onClick = { print("Тут окно 1") }, // .... //todo точно коммент не нейронкой?))
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Показать меню",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.LightGray
                )
            }
        }
        MoneyIcon()
    }

    // Иконка денег
    @Composable
    fun MoneyIcon(): Unit {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 60.dp)
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.TopEnd
        )
        {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.rubblik),
                    contentDescription = "Создает рубль",
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .size(20.dp)
                        .background(Color.Gray)
                )
                Text(
                    text = "$money",
                    modifier = Modifier
                        .height(30.dp)
                        .padding(vertical = 2.dp),
                    fontWeight = FontWeight.Bold
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
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            Text(
                text = "Australia???",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 20.dp)
            )

        }
    }
}

object FanZoneValueHolder {
    var value by mutableIntStateOf(0)
}

class FanZone(
    val tickets: Tickets
) {

    companion object { //todo companion object обычно пишется в конце класса
        var value by mutableStateOf(0)
    }

    @Composable
    fun DrawFanZone() {
        var amountOfFanZone by remember { mutableStateOf(tickets.FanZone) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(20))
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(160.dp, 100.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(vertical = 25.dp),
                            text = "Fan Zone",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold

                        )


                        Text(
                            text = "${amountOfFanZone} tickets left",
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            fontSize = 20.sp,
                            color = Color.Red
                        )

                    }

                }
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart


                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.Gray)
                                .border(3.dp, Color.White, RoundedCornerShape(50))
                        ) {
//                            if (FanZonePrice.toString().length == 4) {
                            Text(
                                text = "$FanZonePrice₽",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 15.dp)
                                    .padding(horizontal = 10.dp),
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )

//                            } else {
//                                Text(
//                                    text = "$FanZonePrice₽",
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                        .padding(vertical = 15.dp)
//                                        .padding(horizontal = 16.dp),
//                                    fontSize = 23.sp,
//                                    fontWeight = FontWeight.Medium,
//                                    color = Color.White
//                                )
//
//                            }

                        }
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(100))
                        ) {
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
                                    fontWeight = FontWeight.Bold
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
                                            fontWeight = FontWeight.Bold
                                        )

                                    in 10..99 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold
                                        )

                                    in 100..999 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold
                                        )

                                }

                            }

                        }

                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(100))
                        ) {
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
                                    fontWeight = FontWeight.Bold
                                )

                            }
                        }


                    }
                }

            }

        }

    }
}

class VipZone {
    val tickets: Tickets

    constructor(_tickets: Tickets) {
        tickets = _tickets

    }

    companion object {
        var value by mutableStateOf(0)
    }


    @Composable
    fun DrawVipZone() {
        var amountOfVipZone by remember { mutableStateOf(tickets.VipZone) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(20))
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-10.dp)

            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(160.dp, 100.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(-15.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(vertical = 25.dp),
                            text = "Vip Zone",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold

                        )


                        Text(
                            text = "${amountOfVipZone} tickets left",
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            fontSize = 20.sp,
                            color = Color.Red
                        )

                    }
                }
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart


                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp, 60.dp)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.Gray)
                                .border(3.dp, Color.White, RoundedCornerShape(50))
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
                                    color = Color.White
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
                                    color = Color.White
                                )

                            }

                        }
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(100))
                        ) {
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
                                    fontWeight = FontWeight.Bold
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
                                            fontWeight = FontWeight.Bold
                                        )

                                    in 10..99 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold
                                        )

                                    in 100..999 ->
                                        Text(
                                            text = "$value",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold
                                        )

                                }

                            }

                        }

                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(100))
                        ) {
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
                                    fontWeight = FontWeight.Bold
                                )

                            }
                        }


                    }
                }

            }

        }
    }

}

class PremiumZone {
    val tickets: Tickets

    constructor(_tickets: Tickets) {
        tickets = _tickets
    }

    companion object {
        var valuePrem by mutableStateOf(0)
    }

    @Composable
    fun DrawPremiumZone() {
        var amountOfPremuimZone by remember { mutableStateOf(tickets.PremiumZone) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(20))
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-10.dp)

            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(160.dp, 100.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(-20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 19.dp)
                                .padding(vertical = 19.dp),
                            text = "Premium",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold

                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 40.dp),

                            text = "Zone",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = "${amountOfPremuimZone} tickets left",
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            fontSize = 20.sp,
                            color = Color.Red
                        )


                    }

                }
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart


                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp, 60.dp)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.Gray)
                                .border(3.dp, Color.White, RoundedCornerShape(50))
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
                                    color = Color.White
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
                                    color = Color.White
                                )

                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(100))
                        ) {
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
                                    fontWeight = FontWeight.Bold
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
                                            fontWeight = FontWeight.Bold
                                        )

                                    in 10..99 ->
                                        Text(
                                            text = "$valuePrem",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold
                                        )

                                    in 100..999 ->
                                        Text(
                                            text = "$valuePrem",
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .padding(vertical = 10.dp),
                                            fontWeight = FontWeight.Bold
                                        )

                                }

                            }

                        }

                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(100))
                        ) {
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
                                    fontWeight = FontWeight.Bold
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
                .padding(vertical = 40.dp)

        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(250.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(20))
                    .background(Color.Magenta)
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
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Text(
                        text = "Purchase",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.LightGray
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
            modifier =
                Modifier
                    .height(40.dp)
                    .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxHeight()
                    .width(200.dp)
                    .clip(RoundedCornerShape(20))
                    .background(Color.Gray.copy(alpha = 0.7f))

            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = 5.dp),
                    text = "Total: $Total ₽ ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



