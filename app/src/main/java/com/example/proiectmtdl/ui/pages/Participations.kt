package com.example.proiectmtdl.ui.pages

import ParticipationItem
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mockedVolunteer

@Composable
fun HelpNPlayMyParticipations(
    volunteerQuestsLazyListState: LazyListState = LazyListState(0, 3),
    modifier: Modifier
) {
    Text(
        text = "Completed quests: ",
        modifier = Modifier.padding(top = 20.dp, start = 10.dp)
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        state = volunteerQuestsLazyListState
    ) {
        items(mockedVolunteer.participations){
            ParticipationItem(
                quest = it
            )
        }
    }
}