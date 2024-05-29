package com.example.proiectmtdl.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proiectmtdl.R
import com.example.proiectmtdl.model.Badge
import com.example.proiectmtdl.model.Event
import com.example.proiectmtdl.model.Quest
import com.example.proiectmtdl.model.Volunteer
import com.example.proiectmtdl.navigateSingleTopTo
import java.util.Date
import java.util.LinkedList

fun NavHostController.navigateToFriend(username: String) = this.navigateSingleTopTo("user/$username")

val mockedBadge = Badge(
    iconId = R.drawable.streaming,
    name = "Good player",
)

val mockedQuest = Quest(
    title = "Help carry stuff",
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

val mockedFriend = Volunteer(
    username = "Mocked friend username",
    email = "email (should not appear)",
    firstName = "FirstName",
    lastName = "LastName",
    friends = LinkedList(),
//    participations = listOf(mockedQuest, mockedQuest, mockedQuest, mockedQuest),
    level = 7,
    currentExperience = 154,
//    prizes = listOf(mockedBadge, mockedBadge, mockedBadge, mockedBadge, mockedBadge, mockedBadge),
)

val friends = LinkedList<Volunteer>(
    listOf(mockedFriend, mockedFriend, mockedFriend, mockedFriend)
)

@Composable
fun FriendsPage(
    friends: List<Volunteer> = LinkedList<Volunteer>(
        listOf(mockedFriend, mockedFriend, mockedFriend, mockedFriend)
    ),
    actualFriendsLazyListState : LazyListState = LazyListState(0, 3),
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    //actual friends
    Column(
        modifier = Modifier.background(Color.Gray)
    ){
        Text(
            text = "Your friends: ",
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            state = actualFriendsLazyListState
        ) {
            items(friends){ friend->
                FriendItem(friend, onClickSeeAccount = {navHostController.navigateToFriend(friend.username)})
            }
        }
    }


    //suggestions
}

@Composable
fun FriendItem(
    friend: Volunteer = mockedFriend,
    onClickSeeAccount: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .clickable { onClickSeeAccount(friend.username) }
    ) {
        Row(
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(60.dp)
                    .background(Color.White)
                    .wrapContentSize(align = Alignment.Center)
            ){
                Text(text = friend.level.toString(),
                    modifier = Modifier.width(10.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ){
                Text(text = friend.username, modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}