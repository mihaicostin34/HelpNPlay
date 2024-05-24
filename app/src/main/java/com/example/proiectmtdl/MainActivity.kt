package com.example.proiectmtdl

import HelpNPlayProfilePage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proiectmtdl.model.Volunteer
import com.example.proiectmtdl.ui.navigation.Friends
import com.example.proiectmtdl.ui.navigation.HelpNPlayNavigationBar
import com.example.proiectmtdl.ui.navigation.News
import com.example.proiectmtdl.ui.navigation.Profile
import com.example.proiectmtdl.ui.navigation.Search
import com.example.proiectmtdl.ui.navigation.UserDestination
import com.example.proiectmtdl.ui.navigation.navigationTabOptions
import com.example.proiectmtdl.ui.pages.FriendsPage
import com.example.proiectmtdl.ui.pages.HelpNPlayNewsList
import com.example.proiectmtdl.ui.pages.HelpNPlaySearchPage
import com.example.proiectmtdl.ui.theme.ProiectMTDLTheme

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route){launchSingleTop = true}




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectMTDLTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen = navigationTabOptions.find { it.route == currentDestination?.route } ?: News

                Scaffold(
                    bottomBar = {
                        HelpNPlayNavigationBar(
                            allScreens = navigationTabOptions,
                            onTabSelected = {newScreen -> navController.navigateSingleTopTo(newScreen.route)},
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
                            )
                        }
                        composable(
                            route = Profile.route
                        ){navBackStackEntry->
                            HelpNPlayProfilePage(
                                dominantColor = MaterialTheme.colorScheme.primaryContainer,
                            )
                        }
                        composable(
                            route = Search.route
                        ){
                            HelpNPlaySearchPage()
                        }
                        composable(
                            route = Friends.route
                        ){
                            FriendsPage(
                                navHostController = navController
                            )
                        }
                        composable(
                            route = "user/{${UserDestination.usernameArg}}",
                            arguments = UserDestination.arguments
                        ){
                            val username = it.arguments?.getString(UserDestination.usernameArg)
                            HelpNPlayProfilePage(
                                username = username!!
                            )
                        }
                    }

                }
            }
        }
    }
}