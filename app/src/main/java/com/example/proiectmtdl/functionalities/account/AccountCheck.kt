package com.example.proiectmtdl.functionalities.account

import android.util.Log
import com.example.proiectmtdl.functionalities.getNetworkService
import com.example.proiectmtdl.model.Company
import com.example.proiectmtdl.model.Organizer
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.UserType
import com.example.proiectmtdl.model.Volunteer

suspend fun checkLogin(loginInformation: LoginInformation): String{
    if(loginInformation.password=="" || loginInformation.username==""){
        return "Invalid"
    }
    return getNetworkService().checkLogin(loginInformation)

}

suspend fun checkSignup(signupInformation: SignupInformation) : SignupInformation.SignupResult{
    //do info checking: confirm pwd matches pwd, no fields are empty and so on
    if(signupInformation.password!= signupInformation.confirmPassword){
        return SignupInformation.SignupResult.PASSWORDS_DONT_MATCH
    }
    val reqRes = getNetworkService().checkSignup(signupInformation)
    return when(reqRes){
        "Username already exists"-> SignupInformation.SignupResult.USERNAME_ALREADY_EXISTS
        "Email already exists"-> SignupInformation.SignupResult.EMAIL_ALREADY_ACCOUNT
        "Ok"-> SignupInformation.SignupResult.SIGNUP_SUCCESS
        "Company does not exist"-> SignupInformation.SignupResult.COMPANY_NOT_EXIST
        else-> SignupInformation.SignupResult.UNKNOWN_ERROR
    }

}

suspend fun checkUsername(username: String) : UserType{
    val userResult : String = getNetworkService().getUserType(username)
    return when(userResult){
        "VOLUNTEER" -> UserType.VOLUNTEER
        "ORGANIZER" -> UserType.ORGANIZER
        "COMPANY" -> UserType.COMPANY
        "ADMIN"-> UserType.ADMIN
        else -> UserType.NOT_SPECIFIED
    }

}