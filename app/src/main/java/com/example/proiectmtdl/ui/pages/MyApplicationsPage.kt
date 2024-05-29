package com.example.proiectmtdl.ui.pages

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proiectmtdl.functionalities.events.CreateEventInformation
import com.example.proiectmtdl.functionalities.events.EventApplication
import com.example.proiectmtdl.functionalities.service

@Preview
@Composable
fun HelpNPlayMyApplications(
    username: String = "Username"
) {
    var applications = remember{ mutableStateListOf<EventApplication>() }
    LaunchedEffect(key1 = 0) {
        val myApplications = service.getApplications(username)
        for(appl : EventApplication in myApplications){
            applications.add(appl)
        }
    }
    val style = TextStyle(
        fontSize =30.sp,
        color = Color.Black
    )
    Column {
        Text(text = "Your applications:", style = style, modifier = Modifier.padding(bottom = 10.dp))
        applications.forEach{
            AppliedEventCard(event = it)
        }
    }
}

@Composable
fun AppliedEventCard(
    event: EventApplication
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .sizeIn(70.dp)
            .background(Color.LightGray)
            .padding(5.dp)
    ) {

        Text(text = "Event ${event.eventTitle}")
        Text(text = "Tasks: ")
        event.quests.forEach{
            Text(text = it.description)
        }
        Text(text = "Status: ${event.status.value}", color = when(event.status){
            EventApplication.ApplicationStatus.ACCEPTED-> Color.Green
            EventApplication.ApplicationStatus.REJECTED-> Color.Red
            EventApplication.ApplicationStatus.APPLIED-> Color.Black
            EventApplication.ApplicationStatus.FINISHED-> Color.Blue
        })
    }
}