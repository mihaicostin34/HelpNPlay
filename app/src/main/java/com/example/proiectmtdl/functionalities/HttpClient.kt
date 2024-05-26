package com.example.proiectmtdl.functionalities

import com.example.proiectmtdl.functionalities.account.LoginInformation
import com.example.proiectmtdl.functionalities.account.SignupInformation
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.Volunteer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

val service: MainNetwork by lazy {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    retrofit.create(MainNetwork::class.java)
}

fun getNetworkService() = service

interface MainNetwork {
    @POST("/users/login")
    suspend fun checkLogin(@Body loginInformation: LoginInformation): String

    @POST("/users/signup")
    suspend fun checkSignup(@Body signupInformation: SignupInformation): String
}

