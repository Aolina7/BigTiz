package com.example.bigtiz

import kotlinx.serialization.encodeToString
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.listOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Dictionary
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataFile = File(filesDir, "DataBase.json")
        if (!dataFile.exists()) {
            assets.open("DataBase.json").use { input ->
                dataFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }

        val jsonString  = dataFile.readText()
        var dictionary = mutableMapOf<String, Int>()

        enableEdgeToEdge()
        setContent {
            DrawSurface()
            DrawPhotoAndDescription()
            DrawMenu()
        }
    }
}

var money by mutableStateOf(10000)
val list = listOf(Color.Gray, Color.Gray, Color.White)
// Данная функция создает поверхность и накладывает на нее фото
@Composable
fun DrawSurface() : Unit {
    Surface(
        modifier = Modifier.fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = "",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }

    UpperOval().DrawUpperOval();
}

// Данная функция создает овал вверху с текстом "Бик Тиз"
class UpperOval {
    @Composable
    fun DrawUpperOval() : Unit {
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
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ){
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
    fun DrawHamburgerMenu() : Unit {
        Box(
            modifier = Modifier.size(30.dp, 56.dp)
        ) {
            IconButton(
                onClick = {print("Тут окно 1")}, // ....
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(Icons.Default.Menu,
                    contentDescription = "Показать меню",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.LightGray)
            }
        }
        MoneyIcon()
    }

    // Иконка денег
    @Composable
    fun MoneyIcon() : Unit {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 60.dp)
                .padding(vertical = 20.dp),
            contentAlignment = androidx.compose.ui.Alignment.TopEnd
        )
        {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(R.drawable.rubblik),
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

@Composable
fun DrawPhotoAndDescription() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp)
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
                painter = painterResource(id = R.drawable.diana),
                contentDescription = "Фотография",
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
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
                        color = Color.Gray.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Диана",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = TextStyle(
                            brush = Brush.linearGradient(list)
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "В мире королевских гонок всегда было место смелым, но сезон 2025 года стал поистине историческим. На трассы Формулы-1 вышла 18-летняя российская гонщица Диана Мухаметзянова — первая женщина за последние 15 лет, получившая место основного пилота.",
                        fontSize = 18.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
fun DrawMenu() {
    Box(
        modifier = Modifier
            .padding(top = 100.dp)
            .height(745.dp)
            .width(1000.dp)
            .background(
                color = Color.Black.copy(0.6f),
            )
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(start = 20.dp, top = 100.dp),
    ) {
        Box(
            modifier = Modifier
                .height(750.dp)
                .width(180.dp)
                .background(
                    color = Color.DarkGray.copy(0.9f),
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.navigation_menu_diana),
                    contentDescription = "диана",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.navigation_menu_anita),
                    contentDescription = "анита",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.navigation_menu_alina),
                    contentDescription = "алина",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.navigation_menu_alexander),
                    contentDescription = "александр",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    contentScale = ContentScale.Crop
                )

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
                                .padding(horizontal = 3.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}




