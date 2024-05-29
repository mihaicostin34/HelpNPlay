package com.example.proiectmtdl.functionalities.events

import com.example.proiectmtdl.functionalities.service

data class EventApplication(
    val volunteer: String = "",
    val eventTitle: String = "",
    val eventCreator: String = "",
    val eventOrganizer: String = "",
    val quests: List<NewQuest> = listOf(),
    val status : ApplicationStatus = ApplicationStatus.APPLIED
) {
    enum class ApplicationStatus(val value: String){
        APPLIED("Applied"),
        ACCEPTED("Accepted"),
        REJECTED("Rejected"),
        FINISHED("Finished")
    }
}

suspend fun checkEventApplication(app: EventApplication): String{
    //TODO: add checks
    return service.applyForEvent(app)
}