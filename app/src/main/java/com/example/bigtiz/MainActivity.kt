package com.example.bigtiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.bigtiz.ui.screen.race_info.presentation.screen.RaceInfoScreen
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen
import com.example.bigtiz.ui.screen.ticket_selection.Tickets
import com.example.bigtiz.ui.screen.ticket_selection.jsonConfig
import com.example.bigtiz.ui.screen.presentation.PilotDetailsScreen
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.ScheduleOfRacesScreen
import kotlinx.coroutines.launch
import java.io.File

@SuppressLint("UnsafeOptInUsageError")
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
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
        val ticket = jsonConfig.decodeFromString<Tickets>(jsonString)

        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = { 4 })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {

                    1 -> RaceInfoScreen(
                        onMenuClick = { scope.launch { pagerState.scrollToPage(0) } },
                        onBuyTicketClick = {} // сюда потом новигацию на экран Александера с покупкой билета
                    )

                    0 -> PilotDetailsScreen(
                        currentRacerId = 1,
                        onNavigateToHome = {
                            println("переход на главную страницу")
                        })
                    
                    2 -> ScheduleOfRacesScreen(onMenuClick = {scope.launch { pagerState.scrollToPage(0) }})
                    3 -> TicketSelectionScreen(ticket, dataFile, onClick = {scope.launch { pagerState.scrollToPage(0) }})
                }
            }
        }
    }
}
