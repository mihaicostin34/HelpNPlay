package com.example.proiectmtdl.model

enum class UserType(name: String) {
    VOLUNTEER("VOLUNTEER"),
    ORGANIZER("ORGANIZER"),
    COMPANY("COMPANY"),
    ADMIN("ADMIN"),
    NOT_SPECIFIED("NOT_SPECIFIED");

    companion object{
        fun fromString(name: String) : UserType{
            return when(name){
                "VOLUNTEER"-> VOLUNTEER
                "ORGANIZER"-> ORGANIZER
                "COMPANY" -> COMPANY
                else -> NOT_SPECIFIED
            }
        }
    }
}