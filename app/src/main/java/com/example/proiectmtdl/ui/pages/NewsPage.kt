package com.example.proiectmtdl.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proiectmtdl.model.Event
import com.example.proiectmtdl.model.NewsItem
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.UserType
import com.example.proiectmtdl.navigateSingleTopTo
import java.util.LinkedList

fun NavHostController.navigateToEvent(eventId: String) = this.navigateSingleTopTo("event/$eventId")


val mockedEvents = LinkedList(listOf(mockedEvent, mockedEvent, mockedEvent))

@Preview
@Composable
fun HelpNPlayNewsItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isOpened: Boolean = false,
    event: Event = mockedEvent,
    onClickSeeEvent : (String)-> Unit = {}
){
    Card (
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clip(CardDefaults.shape)
            .clickable { onClickSeeEvent(event.id) }
        ,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else if (isOpened) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = event.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = event.organizer?.username +  " of " +event.company.username,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            //TODO: maybe add date handling and showing here
            Row(modifier = Modifier.fillMaxWidth()){
                var availableXp = 0
                for (quest in event.quests) {
                    availableXp += quest.experience
                }
                Text(
                    text = "Available experience : $availableXp xp"
                )
            }
        }
    }
}

//the entire news list
@Composable
fun HelpNPlayNewsList(
    modifier: Modifier = Modifier,
    newsLazyListState: LazyListState = LazyListState(0, 3),
    events: List<Event> = mockedEvents,
    navHostController: NavHostController
){
    Box(modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)){
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            state = newsLazyListState
        ) {
            items(events){ item: Event ->
                HelpNPlayNewsItem(
                    modifier =modifier,
                    event =item,
                    onClickSeeEvent = {navHostController.navigateToEvent(item.id)}
                )
            }
        }
    }
}