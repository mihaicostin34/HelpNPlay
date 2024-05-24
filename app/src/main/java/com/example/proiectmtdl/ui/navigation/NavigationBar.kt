package com.example.proiectmtdl.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HelpNPlayNavigationBar(
    allScreens: List<NavigationDestination>,
    onTabSelected : (NavigationDestination) -> Unit,
    currentScreen : NavigationDestination,
    modifier: Modifier = Modifier
){
   NavigationBar {
        navigationTabOptions.forEach{item ->
            NavigationBarItem(
                selected = currentScreen==item,
                onClick = { onTabSelected(item) },
                icon = {Icon(
                    imageVector = item.icon,
                    contentDescription = null
                )}
            )
        }
   }
}

@Preview
@Composable
fun HelpNPlayNavigationBarPreview(
    allScreens: List<NavigationDestination> = navigationTabOptions,
    onTabSelected : (NavigationDestination) -> Unit = {},
    currentScreen : NavigationDestination = News,
    modifier: Modifier = Modifier
){
    NavigationBar {
        navigationTabOptions.forEach{item ->
            NavigationBarItem(
                selected = currentScreen==item,
                onClick = { onTabSelected(item) },
                icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = null
                )}
            )
        }
    }
}