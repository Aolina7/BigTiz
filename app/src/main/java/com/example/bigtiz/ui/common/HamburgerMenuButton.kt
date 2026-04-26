package com.example.bigtiz.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HamburgerMenuButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.size(30.dp, 56.dp)) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Показать меню",
                modifier = Modifier.fillMaxSize(),
                tint = Color.LightGray,
            )
        }
    }
}