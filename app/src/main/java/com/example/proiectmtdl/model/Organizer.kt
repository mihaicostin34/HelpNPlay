package com.example.proiectmtdl.model

class Organizer(
    override val username: String,
    override val email: String,
    override val type: UserType = UserType.ORGANIZER,
    override val profilePicture: String,
    val firstName: String,
    val lastName: String,
) : User(username, email, type, profilePicture) {
}