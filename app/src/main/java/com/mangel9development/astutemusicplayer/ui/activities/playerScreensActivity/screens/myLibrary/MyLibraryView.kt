package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.albumsScreen.AlbumsView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.albumsScreen.AlbumsViewModel
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.artistsScreen.ArtistsView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.artistsScreen.ArtistsViewModel
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.genresScreen.GenresView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.genresScreen.GenresViewModel
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.songsScreen.SongsView
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.songsScreen.SongsViewModel
import com.mangel9development.astutemusicplayer.ui.theme.navigationBackgroundColor
import com.mangel9development.astutemusicplayer.ui.theme.navigationTextColor

@Composable
fun MyLibraryView(viewModel: MyLibraryViewModel){
    // This will house a tabbed view to hold Songs, Artists, Albums, and Genres
    // More info on how to do this can be found here: https://www.freecodecamp.org/news/tabs-in-jetpack-compose/

    val tabIndex=viewModel.tabIndex.observeAsState()

    val tabs=viewModel.tabs

    Column(
        modifier=Modifier
            .fillMaxSize(),
        verticalArrangement=Arrangement.Top
    ){
        ScrollableTabRow( // TODO: find a way for the pages to swipe during the transition
            selectedTabIndex=tabIndex.value!!,
            containerColor=navigationBackgroundColor
        ){
            tabs.forEachIndexed { index, title ->
                Tab(
                    text={ Text(title,color=navigationTextColor) },
                    selected=tabIndex.value!! == index,
                    onClick={ viewModel.updateTabIndex(index) }
                )
            }
        }

        // This might not be the best place to define these. If any issues are caused by this,
        //  consider moving them inside myLibraryViewModel
        val songsViewModel:SongsViewModel=viewModel()
        val artistsViewModel:ArtistsViewModel=viewModel()
        val albumsViewModel:AlbumsViewModel=viewModel()
        val genresViewModel:GenresViewModel=viewModel()

        when (tabIndex.value) {
            0 -> SongsView(viewModel,songsViewModel)
            1 -> ArtistsView(viewModel,artistsViewModel)
            2 -> AlbumsView(viewModel,albumsViewModel)
            3 -> GenresView(viewModel,genresViewModel)
        }
    }
}