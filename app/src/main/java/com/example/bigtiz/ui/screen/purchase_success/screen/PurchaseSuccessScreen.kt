package com.example.bigtiz.ui.screen.purchase_success.screen

import com.example.bigtiz.ui.screen.purchase_success.ViewModel.PurchaseSuccessViewModel


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.R


@Composable
fun PurchaseSuccessScreen(
    viewModel: PurchaseSuccessViewModel,
    onBackClick: () -> Unit
) {

    val state = viewModel.uiState
    val info = state.purchaseInfo

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Box {

            Image(
                painter = painterResource(id = R.drawable.wp6),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.Black.copy(alpha = 0.7f))
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),

                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.rubblik),
                            contentDescription = null,
                            tint = Color.Green,

                            modifier = Modifier.size(80.dp)
                        )

                        Text(
                            text = "Покупка успешна",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Билетов куплено: ${info.totalTickets}",
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        Text(
                            text = "Fan Zone: ${info.fanTickets}",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )

                        Text(
                            text = "VIP Zone: ${info.vipTickets}",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )

                        Text(
                            text = "Premium Zone: ${info.premiumTickets}",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )

                        Text(
                            text = "Цена: ${info.totalPaid} ₽",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Green
                        )

                        Button(
                            onClick = onBackClick,

                            shape = RoundedCornerShape(20.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            )
                        ) {

                            Text(
                                text = "Back",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}