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
import androidx.compose.ui.res.stringResource
import com.mangel9development.astutemusicplayer.R
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.AlbumsScreen
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.ArtistsScreen
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.GenresScreen
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens.SongsScreen
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
        ScrollableTabRow(
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
        when (tabIndex.value) {
            0 -> SongsScreen(viewModel)
            1 -> ArtistsScreen(viewModel)
            2 -> AlbumsScreen(viewModel)
            3 -> GenresScreen(viewModel)
        }
    }
}