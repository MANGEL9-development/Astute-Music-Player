package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.subscreens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary.MyLibraryViewModel

@Composable
fun Subscreen(
    viewModel:MyLibraryViewModel,
    content: @Composable ColumnScope.() -> Unit
){
    Column(
        modifier=Modifier.fillMaxSize().draggable(
            state=viewModel.dragState.value!!,
            orientation=Orientation.Horizontal,
            onDragStarted={},
            onDragStopped={
                viewModel.updateTabIndexBasedOnSwipe()
            }
        ),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement=Arrangement.Top
    ){
        content()
    }
}