package com.example.proiectmtdl.model

class Company(
    override val username: String,
    override val email: String,
    override val type: UserType = UserType.COMPANY,
    override val profilePicture: String,
) : User(username, email, type, profilePicture) {
}