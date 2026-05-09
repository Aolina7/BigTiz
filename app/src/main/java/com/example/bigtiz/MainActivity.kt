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
import com.example.bigtiz.ui.screen.pilot_details.presentation.PilotDetailsScreen
import com.example.bigtiz.ui.screen.race_info.presentation.screen.RaceInfoScreen
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.ScheduleOfRacesScreen
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen
import com.example.bigtiz.ui.screen.ticket_selection.data.TicketRepositoryImpl
import com.example.bigtiz.ui.screen.ticket_selection.domain.PurchaseTicketsUseCase
import com.example.bigtiz.ui.screen.ticket_selection.presentation.TicketViewModel
import com.example.bigtiz.ui.screen.welcome.presentation.WelcomeScreen
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


        val repository = TicketRepositoryImpl(dataFile)


        val purchaseTicketsUseCase =
            PurchaseTicketsUseCase(repository)


        val ticketViewModel = TicketViewModel(
            repository = repository,
            purchaseUseCase = purchaseTicketsUseCase
        )

        enableEdgeToEdge()

        setContent {

            val scope = rememberCoroutineScope()

            val pagerState = rememberPagerState(
                pageCount = { 4 }
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->

                when (page) {

                    0 -> {
                        WelcomeScreen {
                            scope.launch {
                                pagerState.scrollToPage(2)
                            }
                        }
                    }

                    1 -> {

                        PilotDetailsScreen(
                            racerId = 1,

                            onNavigateToHome = {
                                scope.launch {
                                    pagerState.scrollToPage(2)
                                }
                            }
                        )
                    }

                    2 -> {

                        RaceInfoScreen(
                            onMenuClick = {
                                scope.launch {
                                    pagerState.scrollToPage(2)
                                }
                            },

                            onBuyTicketClick = {
                                scope.launch {
                                    pagerState.scrollToPage(3)
                                }
                            }
                        )
                    }

                    3 -> {

                        ScheduleOfRacesScreen(
                            onMenuClick = {
                                scope.launch {
                                    pagerState.scrollToPage(1)
                                }
                            }
                        )
                    }

                    4 -> {

                        TicketSelectionScreen(
                            viewModel = ticketViewModel,

                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(1)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}