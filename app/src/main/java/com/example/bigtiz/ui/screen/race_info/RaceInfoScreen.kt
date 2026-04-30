package com.example.bigtiz.ui.screen.race_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.bigtiz.ui.common.Header

data class RaceResultRow(
    val position: Int,
    val pilotName: String,
    val timeDelta: String,
    val points: Int,
)

@Composable
fun RaceInfoScreen(
    results: List<RaceResultRow> = sampleRaceResults(),
    onMenuClick: () -> Unit = {},
    onBuyTicketClick: () -> Unit = {},
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            RaceInfoTopBar(
                onMenuClick = onMenuClick,
            )

            ImageCard()

            BuyTicketButton(onClick = onBuyTicketClick)

            ResultsTable(results = results)
        }
    }
}

@Composable
private fun RaceInfoTopBar(
    onMenuClick: () -> Unit,
) {
    Header(onMenuClick)
}

@Composable
private fun ImageCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.Black.copy(alpha = 0.25f))
            .border(1.dp, Color.Black.copy(alpha = 0.35f), RoundedCornerShape(22.dp)),
    ) {
        Image(
            painter = painterResource(id = R.drawable.team),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun BuyTicketButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6B6B6B).copy(alpha = 0.7f),
            contentColor = Color(0xFFE0E0E0),
        ),
    ) {
        Text(
            text = "КУПИТЬ БИЛЕТ",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ResultsTable(results: List<RaceResultRow>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF1C1C1C).copy(alpha = 0.55f))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ResultsHeaderRow()
        results.forEach { row ->
            ResultsDataRow(row)
        }
    }
}

@Composable
private fun ResultsHeaderRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HeaderCell("Поз.", Modifier.width(44.dp))
        HeaderCell("Пилот", Modifier.weight(1f))
        HeaderCell("Время", Modifier.width(86.dp))
        HeaderCell("Очки", Modifier.width(52.dp))
    }
}

@Composable
private fun ResultsDataRow(row: RaceResultRow) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F0F0F).copy(alpha = 0.35f))
            .padding(vertical = 8.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PositionBadge(row.position, Modifier.width(44.dp))

        Text(
            text = row.pilotName,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = row.timeDelta,
            color = Color(0xFFA9FF9A),
            fontSize = 13.sp,
            modifier = Modifier.width(86.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = row.points.toString(),
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.width(52.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun HeaderCell(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun PositionBadge(position: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(100))
                .background(Color(0xFF1F1F1F).copy(alpha = 0.75f))
                .border(1.dp, Color.White.copy(alpha = 0.25f), RoundedCornerShape(100)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = position.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun sampleRaceResults(): List<RaceResultRow> = listOf(
    RaceResultRow(1, "Диана", "+13.722S", 18),
    RaceResultRow(2, "Анита", "+15.27S", 15),
    RaceResultRow(3, "Александр", "+15.754S", 12),
    RaceResultRow(4, "Алина", "+23.479S", 10),
)

