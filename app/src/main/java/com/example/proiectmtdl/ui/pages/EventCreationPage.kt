package com.example.proiectmtdl.ui.pages

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proiectmtdl.functionalities.events.CreateEventInformation
import com.example.proiectmtdl.functionalities.events.NewQuest
import com.example.proiectmtdl.functionalities.service
import com.example.proiectmtdl.model.Badge
import com.example.proiectmtdl.model.Quest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.Date
import java.util.LinkedList
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HelpNPlayEventCreation(
    username: String = "Company Name"
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val startDate = rememberDatePickerState()
        val endDate = rememberDatePickerState()
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Create a new event"
            )

            Text(
                text = "Event title: "
            )
            var eventTitle by remember {
                mutableStateOf("")
            }
            TextField(
                value = eventTitle,
                onValueChange = { eventTitle = it}
            )

            Text(
                text = "Event description: "
            )
            var eventDescription by remember {
                mutableStateOf("")
            }
            TextField(
                value = eventDescription,
                onValueChange = { eventDescription = it}
            )

            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            ) {
                Box(
                    modifier = Modifier.padding(end = 30.dp)
                ) {
                    var startDateOpened by remember { mutableStateOf(false) }
                    if (startDateOpened) {
                        DatePickerDialog(
                            onDismissRequest = { startDateOpened = false },
                            confirmButton = {
                                TextButton(onClick = { startDateOpened = false }) {
                                    Text(text = "Ok")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        startDateOpened = false
                                    }
                                ) {
                                    Text("Cancel")
                                }
                            }
                        ) {
                            DatePicker(state = startDate)
                        }
                    } else {
                        TextButton(
                            onClick = { startDateOpened = true },
                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                        ) {
                            Text(text = "Start Date")
                        }
                    }
                }
                Box(
                    modifier = Modifier.padding(end = 30.dp)
                ) {
                    var endDateOpened by remember { mutableStateOf(false) }

                    if (endDateOpened) {
                        DatePickerDialog(
                            onDismissRequest = { endDateOpened = false },
                            confirmButton = {
                                TextButton(onClick = { endDateOpened = false }) {
                                    Text(text = "Ok")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        endDateOpened = false
                                    }
                                ) {
                                    Text("Cancel")
                                }
                            }
                        ) {
                            DatePicker(state = endDate)
                        }
                    } else {
                        TextButton(
                            onClick = { endDateOpened = true },
                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                        ) {
                            Text(text = "End Date")
                        }
                    }
                }


            }
            var quests = remember{mutableStateListOf<NewQuest>()}
            Box(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Quests"
                    )
                    quests.forEachIndexed{
                        index, quest->
                            Row (
                                modifier = Modifier.fillMaxWidth()
                            ){
                                var desc by remember{mutableStateOf("")}
                                var xp by remember{mutableStateOf("")}
                                TextField(
                                    value = quest.description,
                                    onValueChange = {
                                        quests[index] = quest.copy(description = it)
                                    },
                                    placeholder = {Text("Description")}
                                )
                                TextField(
                                    value = quest.experience.toString(),
                                    onValueChange = {
                                        quests[index] = quest.copy(experience = it.toInt())
                                                    },
                                    placeholder = {Text("Xp")}
                                )
                            }
                    }


                    TextButton(
                        onClick = {
//                            questCounter+=1
//                                    descriptions.add("")
//                                    experiences.add(0)
                                  quests.add(NewQuest())
                                  },
                        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(text = "+")
                    }
                }
            }
            //rewards
            var badges = remember{ mutableStateListOf<Badge>() }
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Column{
                    Text(text = "Rewards")

                    badges.forEachIndexed { index, badge ->
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ){
                            TextField(
                                value = badge.name,
                                onValueChange = {
                                    badges[index] = badge.copy(name =it)
                                },
                                placeholder = {Text("Award name")}
                            )
                            TextField(
                                value = badge.count.toString(),
                                onValueChange = {
                                    if(it==""){
                                        badges[index] = badge.copy(count = 0)
                                    }else{
                                        badges[index] = badge.copy(count = it.trim().toInt())
                                    }
                                }
                            )
                        }
                    }

                    TextButton(
                        onClick = {
                                  badges.add(Badge())
                        },
                        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(text = "+")
                    }
                }
            }
            var organizerName by remember {mutableStateOf("")}
            TextField(
                value = organizerName,
                onValueChange = { organizerName = it},
                placeholder = {Text("Organizer name")}
            )
            var loading by remember{ mutableStateOf(false) }
            var confirmation by remember{ mutableStateOf(false) }
            if(confirmation){
                Text(text = "Event has been created!")
            }
            if(loading){
                CircularProgressIndicator()
            }else{
                TextButton(
                    onClick = {
                              loading = true
                        CoroutineScope(Dispatchers.IO).launch{
                            val eventInfo = CreateEventInformation(
                                creator = username,
                                title = eventTitle,
                                description = eventDescription,
                                startDate = Date.from(Instant.ofEpochMilli(startDate.selectedDateMillis!!)),
                                endDate = Date.from(Instant.ofEpochMilli(endDate.selectedDateMillis!!)),
                                organizer = organizerName,
                                quests = quests,
                                badges = badges
                            )
                            val result = service.createEvent(eventInfo)
                            if(result == "Ok"){
                                withContext(Dispatchers.Main){
                                    confirmation = true
                                    loading = false
                                }
                            }
                        }
                    },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text("Create")
                }
            }
        }
    }
}