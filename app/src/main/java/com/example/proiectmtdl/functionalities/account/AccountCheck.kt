package com.example.proiectmtdl.functionalities.account

import android.util.Log
import com.example.proiectmtdl.functionalities.getNetworkService
import com.example.proiectmtdl.model.Company
import com.example.proiectmtdl.model.Organizer
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.UserType
import com.example.proiectmtdl.model.Volunteer

suspend fun checkLogin(loginInformation: LoginInformation): LoginInformation.LoginResult{
    //make sure that no fields are empty
    val reqResult = getNetworkService().checkLogin(loginInformation)
    return when(reqResult){
        "Ok"-> LoginInformation.LoginResult.LOGIN_SUCCESS
        "Invalid"-> LoginInformation.LoginResult.INVALID_INFORMATION
        else-> LoginInformation.LoginResult.UNKNOWN_ERROR
    }
}

suspend fun checkSignup(signupInformation: SignupInformation) : SignupInformation.SignupResult{
    //do info checking: confirm pwd matches pwd, no fields are empty and so on
    if(signupInformation.password!= signupInformation.confirmPassword){
        return SignupInformation.SignupResult.PASSWORDS_DONT_MATCH
    }
    val reqRes = getNetworkService().checkSignup(signupInformation)

    return SignupInformation.SignupResult.SIGNUP_SUCCESS
}