package com.example.proiectmtdl.model

import java.util.LinkedList

class Volunteer(
    override val username: String,
    override val email: String,
    override val type: UserType = UserType.VOLUNTEER,
    override val profilePicture: String,
    val firstName: String,
    val lastName: String,
    val friends: LinkedList<Volunteer>,
    val participations: List<Quest>,
    val level: Int,
    val currentExperience: Int,
    val prizes: List<Badge>
) : User(username, email, type, profilePicture) {
}