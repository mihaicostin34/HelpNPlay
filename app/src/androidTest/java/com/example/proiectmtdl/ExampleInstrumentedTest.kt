package com.example.proiectmtdl

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.proiectmtdl.functionalities.account.LoginInformation
import com.example.proiectmtdl.functionalities.service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.proiectmtdl", appContext.packageName)
    }

    @Test
    suspend fun getAllEvents() = runTest{
        CoroutineScope(Dispatchers.IO).launch {
            val events = service.getAllEvents()
        }
    }


    @Test
    suspend fun checkUserYpe() = runTest{
        CoroutineScope(Dispatchers.IO).launch {
            val userType = service.getUserType("mc34")
            assertEquals(userType, "VOLUNTEER")
        }
    }

    @Test
    suspend fun checkLoginData() = runTest {
        CoroutineScope(Dispatchers.IO).launch {
            val loginInfo = LoginInformation(
                username = "mc34",
                password="wrong_password"
            )
            val wrong_login = service.checkLogin(loginInfo)
            assertEquals(wrong_login, "Invalid")

            val correctLogin =LoginInformation(
                username = "mc34",
                password="12"
            )
            val correct_login = service.checkLogin(correctLogin)
            assertEquals(correct_login, "Ok")
        }
    }
}