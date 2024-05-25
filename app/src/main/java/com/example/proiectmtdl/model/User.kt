package com.example.proiectmtdl.model

open class User(
    open val username: String = "",
    open val email: String = "",
    open val type: UserType = UserType.NOT_SPECIFIED,
) {
}