package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.parts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mangel9development.astutemusicplayer.R
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.navigation.Routes
import com.mangel9development.astutemusicplayer.ui.theme.navigationBackgroundColor

@Composable
fun BottomBar(
    nav:NavHostController
) {
    // TODO: change bottom bar background color
    NavigationBar(
        containerColor = navigationBackgroundColor
    ){
        val backStack by nav.currentBackStackEntryAsState()
        val destination=backStack?.destination
        NavigationBarItem(
            selected = nav.currentBackStackEntry?.destination?.route==Routes.MyLibrary.route,
            onClick = {
                nav.navigate(Routes.MyLibrary.route){
                    launchSingleTop=true
                    popUpTo(Routes.MyLibrary.route){ inclusive=false }
                }
            },
            icon = {
                Icon(
                    Icons.Default.List,
                    contentDescription=stringResource(R.string.libraryNavButtonDescription)
                )
            },
            label = {
                Text(stringResource(R.string.library))
            }
        )
        NavigationBarItem(
            selected = nav.currentBackStackEntry?.destination?.route==Routes.Search.route,
            onClick = {
                nav.navigate(Routes.Search.route)
            },
            icon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription=stringResource(R.string.searchNavButtonDescription)
                )
            },
            label = {
                Text(stringResource(R.string.search))
            }
        )
        NavigationBarItem(
            selected = nav.currentBackStackEntry?.destination?.route==Routes.MyPlaylists.route,
            onClick = {
                nav.navigate(Routes.MyPlaylists.route)
            },
            icon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription=stringResource(R.string.playlistsNavButtonDescription)
                )
            },
            label = {
                Text(stringResource(R.string.playlists))
            }
        )
    }
}