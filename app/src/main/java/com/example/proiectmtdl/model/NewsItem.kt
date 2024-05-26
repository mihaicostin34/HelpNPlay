package com.example.proiectmtdl.model

import java.util.LinkedList

class NewsItem(
    val creator: User = Volunteer("username", "email", firstName = "First Name", lastName = "Last Name", friends = LinkedList<Volunteer>(), participations = LinkedList<Quest>(), level = 20, currentExperience = 20, prizes = LinkedList(listOf())),
    val id: Int = 200
) {
}