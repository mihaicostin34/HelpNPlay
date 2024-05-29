package com.example.proiectmtdl.functionalities.events

import com.example.proiectmtdl.model.Badge
import com.example.proiectmtdl.model.Quest
import java.util.Date

data class CreateEventInformation (
    val creator: String = "",
    val title: String = "",
    val description: String = "",
    val startDate: Date =Date(),
    val endDate: Date = Date(),
    val organizer: String ="",
    val quests: List<NewQuest> = listOf(),
    val badges: List<Badge> =listOf()
){
}