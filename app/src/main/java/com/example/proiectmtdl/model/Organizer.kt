package com.example.proiectmtdl.model

class Organizer(
    override val username: String,
    override val email: String,
    override val type: UserType = UserType.ORGANIZER,
    val firstName: String,
    val lastName: String,
) : User(username, email, type) {
}