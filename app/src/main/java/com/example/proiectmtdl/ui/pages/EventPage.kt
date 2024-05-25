package com.example.proiectmtdl.ui.pages

import BadgeItem
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proiectmtdl.model.AwardsMapper
import com.example.proiectmtdl.model.Event
import com.example.proiectmtdl.model.Organizer
import com.example.proiectmtdl.model.Quest
import com.example.proiectmtdl.model.Volunteer
import com.example.proiectmtdl.navigateSingleTopTo
import mockedVolunteer
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
val mockedEvent = Event(
    id = "Mocked event id",
    name = "Mocked event name",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    dates = LinkedList<Date>(),
    organizer = Organizer(
        username = "Organizer username",
        email = "Org email",
        firstName = "First organizer name",
        lastName = "Last organizer name"
    ),
    participants = LinkedList(listOf(mockedFriend, mockedVolunteer)),
    quests = listOf(mockedQuest, mockedQuest2, mockedQuest3),
    awards = LinkedList(listOf(Pair(mockedBadge, 2), Pair(mockedBadge, 3)))
)

@Composable
fun HelpNPlayEventPage(
    eventId: String = "param event id",
    modifier: Modifier = Modifier
) {
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
                text = eventId, //TODO: change this with the event name
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
                            text = "Organized by: ${mockedEvent.organizer?.username}",
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Text(
                            text = "Description: ${mockedEvent.description}",
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
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
                for(quest in mockedEvent.quests){
                    ImprovedQuestItem(quest)
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
                    for(pair in mockedEvent.awards){
                        val award = pair.first
                        val count = pair.second
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 10.dp,end = 20.dp)
                        ) {
                            BadgeItem(prize = award, modifier = Modifier)
                            Text(
                                text = award.name)
                            Text(
                                text = "x${count}"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ImprovedQuestItem(
    quest: Quest = mockedQuest2,
    isSelected: Boolean = false,
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
            Text(text = quest.title)
            val eventName = if(quest.event==null) "Unknown event" else quest.event.name
            val dates = if(quest.event?.dates?.size==2){
                //to from
                "To from"
            }else if(quest.event?.dates?.size==1){
                //date
                "On"
            }else{
                "Unknown dates"
            }
            Text(text  = "$eventName - $dates")
        }
        Column(
            modifier = Modifier
                .wrapContentSize(align = Alignment.CenterStart)
                .padding(start = 10.dp)
        ) {
            Text("${quest.experience} xp", modifier = Modifier)
        }
        val checked by remember{ mutableStateOf(false) }
        Checkbox(
            checked = checked,
            onCheckedChange ={},
        )
    }
}

