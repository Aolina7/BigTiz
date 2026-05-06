package com.example.bigtiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import com.example.bigtiz.ui.screen.pilot_details.PilotDetailsScreen
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.bigtiz.ui.screen.race_info.RaceInfoScreen
import com.example.bigtiz.ui.screen.schedule_of_races.ScheduleOfRacesScreen
import com.example.bigtiz.ui.screen.ticket_selection.data.TicketRepositoryImpl
import com.example.bigtiz.ui.screen.ticket_selection.domain.PurchaseTicketUseCase
import com.example.bigtiz.ui.screen.ticket_selection.presentation.TicketSelectionScreen
import com.example.bigtiz.ui.screen.ticket_selection.domain.Tickets
import com.example.bigtiz.ui.screen.ticket_selection.presentation.TicketViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
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
        val jsonConfig: Json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val jsonString  = dataFile.readText()
        val ticket = jsonConfig.decodeFromString<Tickets>(jsonString)
        val repository = TicketRepositoryImpl(dataFile)
        val purchaseUseCase = PurchaseTicketUseCase(repository)

        val viewModel = TicketViewModel(
            purchaseUseCase = purchaseUseCase,
            repository = repository
        )

        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = { 4 })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> PilotDetailsScreen()
                    1 -> RaceInfoScreen(onMenuClick = {scope.launch { pagerState.scrollToPage(0) }})
                    2 -> ScheduleOfRacesScreen(onMenuClick = {scope.launch { pagerState.scrollToPage(0) }})
                    3 -> TicketSelectionScreen(viewModel, onClick = {scope.launch { pagerState.scrollToPage(0) }})
                }
            }
        }
    }
}
