package com.example.proiectmtdl.model

open class User(
    open val username: String,
    open val email: String,
    open val type: UserType,
    open val profilePicture: String, //TODO: find a better way to do this
) {
}