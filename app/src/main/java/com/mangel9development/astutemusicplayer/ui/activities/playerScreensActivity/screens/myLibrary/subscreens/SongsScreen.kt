package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mangel9development.astutemusicplayer.model.Song
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.MyLibraryViewModel

@Composable
fun SongsScreen(viewModel:MyLibraryViewModel){
    Subscreen(viewModel){
        LazyColumn{
            items(viewModel.songs()){
                SongRow(song=it)
            }
        }
    }
}

// this could be moved outside of this class if need be
@Composable
fun SongRow(song:Song){
    Row(
        modifier=Modifier
            .fillMaxWidth()
            .padding(8.dp) // this could be defined somewhere else if need be
    ){
        Text(song.title)
        Text(song.artist.name)
    }
}