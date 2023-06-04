package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.MyLibraryViewModel

@Composable
fun ArtistsScreen(viewModel:MyLibraryViewModel) {
    Subscreen(viewModel){
        Text("Artists")
    }
}