package com.example.proiectmtdl.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HelpNPlayEventCreation(
    username: String = "Company Name"
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Text(
                text = "Create a new event"
            )

            Text(
                text = "Event title: "
            )
            TextField(
                value = "",
                onValueChange ={}
            )

            Text(
                text = "Event description: "
            )
            TextField(
                value = "",
                onValueChange ={}
            )

            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            ){
                Box(
                    modifier = Modifier.padding(end = 30.dp)
                ){
                    var startDateOpened by remember{mutableStateOf(false)}
                    val state = rememberDatePickerState()
                    if(startDateOpened){
                        DatePickerDialog(
                            onDismissRequest = { startDateOpened = false },
                            confirmButton = {
                                TextButton(onClick = {startDateOpened = false}) {
                                    Text(text = "Ok")
                                }
                            },
                            dismissButton = {TextButton(
                                onClick = {
                                    startDateOpened = false
                                }
                            ) {
                                Text("Cancel")
                            }}
                        ) {
                            DatePicker(state = state)
                        }
                    }else{
                        TextButton(onClick = { startDateOpened = true },
                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
                            Text(text = "Start Date")
                        }
                    }
                }
                Box(
                    modifier = Modifier.padding(end = 30.dp)
                ){
                    var endDateOpened by remember{mutableStateOf(false)}
                    val state = rememberDatePickerState()
                    if(endDateOpened){
                        DatePickerDialog(
                            onDismissRequest = { endDateOpened = false },
                            confirmButton = {
                                TextButton(onClick = {endDateOpened = false}) {
                                    Text(text = "Ok")
                                }
                            },
                            dismissButton = {TextButton(
                                onClick = {
                                    endDateOpened = false
                                }
                            ) {
                                Text("Cancel")
                            }}
                        ) {
                            DatePicker(state = state)
                        }
                    }else{
                        TextButton(onClick = { endDateOpened = true },
                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
                            Text(text = "End Date")
                        }
                    }
                }


            }

            //TODOs:
            // 1. Add quests to event
            // 2. add prizes
            // 3. Add organizer


            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text("Create")
            }
        }
    }
}