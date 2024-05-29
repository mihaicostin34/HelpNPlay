package com.example.proiectmtdl.ui.pages

import BadgeItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proiectmtdl.functionalities.events.CreateEventInformation
import com.example.proiectmtdl.functionalities.events.EventApplication
import com.example.proiectmtdl.functionalities.events.NewQuest
import com.example.proiectmtdl.functionalities.events.checkEventApplication
import com.example.proiectmtdl.functionalities.service
import com.example.proiectmtdl.model.Event
import com.example.proiectmtdl.model.Organizer
import com.example.proiectmtdl.model.Quest
import com.example.proiectmtdl.model.UserType
import com.example.proiectmtdl.model.Volunteer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mockedVolunteer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.LinkedList

val mockedQuest2 = Quest(
    title = "Give awards",
    description = "This is a longer description. This should not be visible on the user's profile",
    event = Event(
        id = "eventId",
        name = "Event Name",
        description = "longer description. do not feature this in the profile",
        dates = LinkedList<Date>(),
        organizer = null,
        participants = LinkedList<Volunteer>(),
        quests = listOf()
    )
)

val mockedQuest3 = Quest(
    title = "Help supply stuff",
    description = "This is a longer description. This should not be visible on the user's profile",
    event = Event(
        id = "eventId",
        name = "Event Name",
        description = "longer description. do not feature this in the profile",
        dates = LinkedList<Date>(),
        organizer = null,
        participants = LinkedList<Volunteer>(),
        quests = listOf()
    )
)

@Composable
fun HelpNPlayEventPage(
    eventTitle: String,
    userType: UserType,
    username: String,
    modifier: Modifier = Modifier
) {
    var event by remember{mutableStateOf(CreateEventInformation())}
    LaunchedEffect(key1 = 0) {
        event = service.getEventDetails(eventTitle)
    }
    var chosenQuests = remember{ mutableStateListOf<NewQuest>() }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(5.dp)
    ) {
        //name of the event
        val style = TextStyle(
            fontSize =30.sp,
            color = Color.Black
        )
        val subtitleStyle = TextStyle(
            fontSize = 20.sp
        )
        Column(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = eventTitle, //TODO: change this with the event name
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp, bottom = 10.dp)
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                style = style
            )
            //details
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp)) {

                Text(
                    text = "Event details: ",
                    modifier = Modifier.padding(bottom = 10.dp),
                    style = subtitleStyle
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column {
                        Text(
                            text = "Organized by: ${event.organizer}",
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Text(
                            text = "Description: ${event.description}",
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        val eventName = event.title
                        val sdf = SimpleDateFormat("dd/M/yyy HH:mm:ss")
                        val dates = "From ${sdf.format(event.startDate)} to ${sdf.format(event.endDate)}"
                        Text(text  = "$eventName - $dates")
                    }
                }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .height(3500.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Available Quests:",
                    modifier = Modifier.padding(start = 10.dp),
                    style = subtitleStyle
                )
                for(quest in event.quests){
                    ImprovedQuestItem(quest, userType, onCheckChange = {
                        if(it){
                            chosenQuests.add(quest)
                        }else{
                            chosenQuests.remove(quest)
                        }
                    })
                }
                Text(
                    text = "Available Prizes:",
                    modifier = Modifier.padding(start = 10.dp),
                    style = subtitleStyle
                )
                Row(
                    Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp)
                ){
                    for(pair in event.badges){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 10.dp,end = 20.dp)
                        ) {
                            BadgeItem(prize = pair, modifier = Modifier)
                            Text(
                                text = pair.name)
                            Text(
                                text = "x${pair.count}"
                            )
                        }
                    }
                }
                if(userType==UserType.VOLUNTEER){
                    var loading by remember {
                        mutableStateOf(false)
                    }
                    var ok by remember{
                        mutableStateOf("")
                    }
                    var msg by remember {
                        mutableStateOf(false)
                    }
                    if(!loading){
                        Button(
                            onClick =
                            { //coroutine: send application to server
                                val application = EventApplication(
                                    volunteer = username,
                                    eventTitle = eventTitle,
                                    quests = chosenQuests,
                                    eventCreator = event.creator,
                                    eventOrganizer = event.organizer
                                )
                                loading = true
                                CoroutineScope(Dispatchers.IO).launch{
                                    val reqRes = checkEventApplication(application)
                                    if(reqRes=="Ok"){
                                        withContext(Dispatchers.Main){
                                            ok = "Successfully applied"
                                            loading = false
                                        }
                                    }else{
                                        withContext(Dispatchers.Main){
                                            ok = reqRes
                                            loading = false
                                        }
                                    }
                                }

                            }
                        ) {
                            Text(text = "Apply")
                        }
                    }else{
                        CircularProgressIndicator()
                    }
                    if(msg){
                        Text(text = ok)
                    }
                }
            }

        }
    }
}

@Composable
fun ImprovedQuestItem(
    quest: NewQuest,
    userType: UserType,
    isSelected: Boolean = false,
    onCheckChange: (Boolean)->Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
//                   .fillMaxWidth()
                .padding(end = 10.dp)
        ){
            Text(text = quest.description)
        }
        Column(
            modifier = Modifier
                .wrapContentSize(align = Alignment.CenterStart)
                .padding(start = 10.dp)
        ) {
            Text("${quest.experience} xp", modifier = Modifier)
        }
        if(userType==UserType.VOLUNTEER){
            var checked by remember{ mutableStateOf(false) }
            Checkbox(
                checked = checked,
                onCheckedChange ={onCheckChange(it)
                                 checked = !checked
                },
            )
            
        }
    }
}

