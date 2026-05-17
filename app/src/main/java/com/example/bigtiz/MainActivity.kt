package com.example.bigtiz

import ScheduleOfRacesScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.bigtiz.ui.screen.pilot_details.presentation.PilotDetailsScreen
import com.example.bigtiz.ui.screen.purchase_success.ViewModel.PurchaseSuccessViewModel
import com.example.bigtiz.ui.screen.purchase_success.screen.PurchaseSuccessScreen
import com.example.bigtiz.ui.screen.race_info.presentation.route.RaceInfoRoute
import com.example.bigtiz.ui.screen.schedule_of_races.data.ScheduleOfRacesRepositoryImpl
import com.example.bigtiz.ui.screen.schedule_of_races.domain.usecase.GetRacesUseCase
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.viewmodel.ScheduleOfRacesViewModel
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.viewmodel.ScheduleOfRacesViewModelFactory
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen
import com.example.bigtiz.ui.screen.ticket_selection.data.TicketRepositoryImpl
import com.example.bigtiz.ui.screen.ticket_selection.domain.PurchaseTicketsUseCase
import com.example.bigtiz.ui.screen.ticket_selection.presentation.TicketViewModel
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

        val repositorySchedule = ScheduleOfRacesRepositoryImpl()

        val getRacesUseCase = GetRacesUseCase(repositorySchedule)

        val factory = ScheduleOfRacesViewModelFactory(getRacesUseCase)

        val scheduleOfRacesViewModel =
            ViewModelProvider(this, factory)
                .get(ScheduleOfRacesViewModel::class.java)

        val repository = TicketRepositoryImpl(dataFile)

        val purchaseTicketsUseCase = PurchaseTicketsUseCase(repository)

        val ticketViewModel = TicketViewModel(
            repository = repository,
            purchaseUseCase = purchaseTicketsUseCase
        )

        val purchaseSuccessViewModel = PurchaseSuccessViewModel()

        enableEdgeToEdge()

        setContent {

            val scope = rememberCoroutineScope()

            val pagerState = rememberPagerState(
                pageCount = { 5 }
            )

            var selectedPlace by remember {
                mutableStateOf("Australia")
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),

                userScrollEnabled = pagerState.currentPage < 1 || (pagerState.currentPage == 1 && pagerState.targetPage == 0)

            ) { page ->

                when (page) {

                    0 -> {

                        PilotDetailsScreen(
                            racerId = 1,

                            onNavigateToHome = {
                                scope.launch {
                                    pagerState.scrollToPage(1)
                                }
                            }
                        )
                    }

                    1 -> {

                        RaceInfoRoute(

                            onMenuClick = {
                                scope.launch {
                                    pagerState.scrollToPage(0)
                                }
                            },

                            onBuyTicketClick = {
                                scope.launch {
                                    pagerState.scrollToPage(2)
                                }
                            }
                        )
                    }

                    2 -> {

                        ScheduleOfRacesScreen(
                            viewModel = scheduleOfRacesViewModel,

                            onMenuClick = {
                                scope.launch {
                                    pagerState.scrollToPage(0)
                                }
                            },

                            onTicketClick = { place ->

                                selectedPlace = place

                                scope.launch {
                                    pagerState.scrollToPage(3)
                                }
                            }
                        )
                    }

                    3 -> {

                        TicketSelectionScreen(
                            viewModel = ticketViewModel,

                            selectedPlace = selectedPlace,

                            purchaseSuccessViewModel = purchaseSuccessViewModel,

                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(0)
                                }
                            },

                            onPurchaseSuccess = {
                                scope.launch {
                                    pagerState.scrollToPage(4)
                                }
                            }
                        )
                    }

                    4 -> {

                        PurchaseSuccessScreen(
                            viewModel = purchaseSuccessViewModel,

                            onBackClick = {
                                scope.launch {
                                    pagerState.scrollToPage(3)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

