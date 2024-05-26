package com.example.proiectmtdl.model

import java.util.LinkedList

class Volunteer(
    override val username: String,
    override val email: String,
    override val type: UserType = UserType.VOLUNTEER,
    val firstName: String,
    val lastName: String,
    val friends: LinkedList<Volunteer> = LinkedList(),
    val participations: LinkedList<Quest> = LinkedList(),
    val level: Int = 0,
    val currentExperience: Int = 0,
    val prizes: LinkedList<Badge> = LinkedList(),
    val password: String = ""
) : User(username, email, type) {
}