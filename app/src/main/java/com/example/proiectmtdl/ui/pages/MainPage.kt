package com.example.proiectmtdl.ui.pages

import HelpNPlayProfilePage
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proiectmtdl.functionalities.account.checkUsername
import com.example.proiectmtdl.model.UserType
import com.example.proiectmtdl.navigateSingleTopTo
import com.example.proiectmtdl.ui.navigation.Event
import com.example.proiectmtdl.ui.navigation.Friends
import com.example.proiectmtdl.ui.navigation.HelpNPlayNavigationBar
import com.example.proiectmtdl.ui.navigation.News
import com.example.proiectmtdl.ui.navigation.Profile
import com.example.proiectmtdl.ui.navigation.Search
import com.example.proiectmtdl.ui.navigation.UserDestination
import com.example.proiectmtdl.ui.navigation.navigationTabOptions

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HelpnPlayMainPage(
    username: String
) {
    var userType by remember{mutableStateOf(UserType.NOT_SPECIFIED)}
    LaunchedEffect(key1 = 0) {
        userType = checkUsername(username)
        Log.d(null, userType.name)
    }
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = navigationTabOptions.find { it.route == currentDestination?.route } ?: News
    Scaffold(
        bottomBar = {
            HelpNPlayNavigationBar(
                allScreens = navigationTabOptions,
                onTabSelected =
                    {
                        newScreen -> navController.navigateSingleTopTo(newScreen.route)
                    },
                currentScreen = currentScreen
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = News.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(
                route = News.route
            ){
                HelpNPlayNewsList(
                    navHostController = navController
                )
            }
            composable(
                route = Profile.route
            ){
                HelpNPlayProfilePage(
                    username = username,
                    dominantColor = MaterialTheme.colorScheme.primaryContainer,
                    currentUser = true
                )
            }
            composable(
                route = Search.route
            ){
                HelpNPlaySearchPage(username, userType)
            }
            composable(
                route = Friends.route
            ){
                FriendsPage(
                    navHostController = navController
                )
            }
            composable(
                route = "/user"
            ){
                HelpNPlayProfilePage(
                    username = username
                )
            }
            composable(
                route = "event/{${Event.eventArg}}",
                arguments = Event.arguments
            ){
                val eventId = it.arguments?.getString(Event.eventArg)
                HelpNPlayEventPage(eventId!!, userType, username)
            }
        }

    }
}