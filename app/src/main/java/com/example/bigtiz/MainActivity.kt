package com.example.bigtiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bigtiz.ui.screen.pilot_details.PilotDetailsScreen
import com.example.bigtiz.ui.screen.race_info.RaceInfoScreen
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen
import com.example.bigtiz.ui.screen.ticket_selection.Tickets
import com.example.bigtiz.ui.screen.ticket_selection.jsonConfig
import java.io.File

@SuppressLint("UnsafeOptInUsageError")
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
        val ticket = jsonConfig.decodeFromString<Tickets>(jsonString)

        enableEdgeToEdge()
        setContent {
            //TicketSelectionScreen(ticket, dataFile)
            //RaceInfoScreen()
            PilotDetailsScreen()
        }
    }
}
