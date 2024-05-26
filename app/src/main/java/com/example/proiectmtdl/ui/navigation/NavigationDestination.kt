package com.example.proiectmtdl.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavigationDestination{
    val route : String
    val icon: ImageVector
}

object News : NavigationDestination{
    override val route: String = "news"
    override val icon = Icons.Default.Home
}

object Profile: NavigationDestination{
    override val route: String = "profile"
    override val icon = Icons.Default.AccountCircle
}

object Search: NavigationDestination{
    override val route: String = "search"
    override val icon: ImageVector = Icons.Default.Search
}

//TODO: create and add page
object Friends: NavigationDestination{
    override val route: String = "friends"
    override val icon: ImageVector = Icons.Default.AccountBox
}

object UserDestination : NavigationDestination{
    const val usernameArg = "username"
    override val route: String = "user/{${usernameArg}}"
    val arguments = listOf(
        navArgument(usernameArg){type = NavType.StringType}
    )
    override val icon: ImageVector = Icons.Default.Star
}

object Event: NavigationDestination{
    const val eventArg = "eventId"
    override val route: String = "event/{${eventArg}}"
    val arguments = listOf(
        navArgument(eventArg){type = NavType.StringType}
    )
    override val icon: ImageVector = Icons.Default.Star
}

object Login: NavigationDestination{
    override val route = "login"
    override val icon = Icons.Default.Star
}

object Signup: NavigationDestination{
    override val route = "signup"
    override val icon = Icons.Default.Star
}

object Start: NavigationDestination{
    override val route = "start"
    override val icon = Icons.Default.Star
}

object FullMainPage: NavigationDestination{
    const val usernameArg = "username"
    override val route = "main/{${usernameArg}}"
    override val icon = Icons.Default.Star
    val arguments = listOf(navArgument(usernameArg){type = NavType.StringType})
}

val navigationTabOptions = listOf(News, Search, Friends, Profile)