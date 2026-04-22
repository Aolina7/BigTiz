package com.example.bigtiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bigtiz.ui.theme.BigTizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BigTizTheme {
                DrawSurface()
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