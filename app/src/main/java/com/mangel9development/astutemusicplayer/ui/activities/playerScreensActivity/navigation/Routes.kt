package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.navigation

sealed class Routes(val route: String) {
    object MyLibrary : Routes("MyLibrary")
    object Search : Routes("Search")
    object MyPlaylists : Routes("MyPlaylists")
    object MyCharts : Routes("MyCharts")
}