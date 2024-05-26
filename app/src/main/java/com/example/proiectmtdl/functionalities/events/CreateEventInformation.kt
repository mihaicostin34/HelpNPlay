package com.example.proiectmtdl.functionalities.events

import java.util.Date

data class CreateEventInformation (
    val creator: String = "",
    val title: String = "",
    val description: String,
    val startDate: Date,
    val endDate: Date
){
}