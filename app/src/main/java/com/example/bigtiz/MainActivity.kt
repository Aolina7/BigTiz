package com.example.bigtiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.ui.theme.BigTizTheme

var money by mutableStateOf(1000)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BigTizTheme {
                DrawSurface()
                UpperOval().DrawUpperOval()
            }
        }
    }
}

@Composable
fun DrawSurface() {
    Surface(
        modifier = Modifier.fillMaxSize() //заставляет Surface занять всю доступную ширину и высоту экрана

    ) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = "", //описание фона
            contentScale = androidx.compose.ui.layout.ContentScale.Crop //Crop увеличивает изображение, чтобы полность покрывало экран
        )
    }
}

//шапка экрана
class UpperOval {
    @Composable
    fun DrawUpperOval() : Unit {
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

    //контекстное меню
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

    //деньги
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
                Image(
                    painter = painterResource(id = R.drawable.rubblik),
                    contentDescription = "Сумма на счету",
                    modifier = Modifier.size(40.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop

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
