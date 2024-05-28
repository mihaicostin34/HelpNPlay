package com.example.proiectmtdl.functionalities.account

import com.example.proiectmtdl.model.UserType

data class SignupInformation(
    var email: String = "",
    var username: String  = "",
    var password: String = "",
    var confirmPassword: String = "",
    var accountType: UserType = UserType.NOT_SPECIFIED,
    var firstName: String = "",
    var lastName: String = "",
    var companyName: String =""
){
    enum class SignupResult(val message: String){
        SIGNUP_SUCCESS("Ok"),
        PASSWORDS_DONT_MATCH("Passwords don't match"),
        USERNAME_ALREADY_EXISTS("Username already exists"),
        EMAIL_ALREADY_ACCOUNT("There already is an account with that email"),
        TYPE_NOT_SELECTED("Please select account type"),
        COMPANY_NOT_EXIST("The selected company does not exist"),
        NOT_EMAIL("Please enter a valid email password"),
        UNKNOWN_ERROR("Something went wrong. Please try again")
    }
}