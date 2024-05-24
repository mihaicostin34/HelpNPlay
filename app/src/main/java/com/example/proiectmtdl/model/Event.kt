package com.example.proiectmtdl.model

import java.util.Date
import java.util.LinkedList

class Event(
    val id: String, //TODO: check if this is ok
    val name: String,
    val description: String,
    val dates: LinkedList<Date>,
    val organizer: Organizer?,
    val participants: LinkedList<Volunteer>,
    val quests: List<Quest>
) {
}