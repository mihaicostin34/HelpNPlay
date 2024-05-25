package com.example.proiectmtdl.model

class Company(
    override val username: String ="company username",
    override val email: String = "companyemail",
    override val type: UserType = UserType.COMPANY,
) : User(username, email, type) {
}