package com.example.proiectmtdl.functionalities.account

class LoginInformation(
    val username: String,
    val password: String
) {

    enum class LoginResult(val message: String){
        LOGIN_SUCCESS("Ok"),
        INVALID_INFORMATION("Password and username don't correspond"),
        UNKNOWN_ERROR("Something went wront. Please try again")
    }
}