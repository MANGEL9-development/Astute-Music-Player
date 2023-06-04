package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.navigation

import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.MyLibraryView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.MyLibraryViewModel
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myPlaylists.MyPlaylistsViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myPlaylists.MyPlaylistsView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.search.SearchView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.search.SearchViewModel

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
){
    val myLibraryVM:MyLibraryViewModel=viewModel()
    val searchVM:SearchViewModel=viewModel()
    val myPlaylistsVM:MyPlaylistsViewModel=viewModel()
    NavHost(
        navController=navController,
        startDestination=Routes.MyLibrary.route
    ) {
        composable(Routes.MyLibrary.route){
            MyLibraryScreen(myLibraryVM,navController)
        }
        composable(Routes.Search.route){
            SearchScreen(searchVM,navController)
        }
        composable(Routes.MyPlaylists.route){
            MyPlaylistsScreen(myPlaylistsVM,navController)
        }
        // TODO: add another screen to show the "My Charts" screen
    }
}
@ExperimentalFoundationApi
@Composable
fun MyLibraryScreen(viewModel:MyLibraryViewModel,
                    navigator:NavHostController) {
    MyLibraryView(viewModel)
}
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun SearchScreen(viewModel:SearchViewModel,
                 navigator:NavHostController) {
    SearchView(viewModel)
}
@ExperimentalFoundationApi
@Composable
fun MyPlaylistsScreen(viewModel:MyPlaylistsViewModel,
                      navigator:NavHostController) {
    MyPlaylistsView(viewModel)
}