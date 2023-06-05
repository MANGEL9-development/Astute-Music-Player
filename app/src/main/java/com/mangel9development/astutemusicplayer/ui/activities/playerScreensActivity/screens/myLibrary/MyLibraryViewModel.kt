package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.screens.myLibrary

import android.app.Application
import androidx.compose.foundation.gestures.DraggableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mangel9development.astutemusicplayer.R

class MyLibraryViewModel(app:Application):AndroidViewModel(app){
    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs=listOf(
        app.getString(R.string.songs),
        app.getString(R.string.artists),
        app.getString(R.string.albums),
        app.getString(R.string.genres)
    )

    var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState{ delta ->
        isSwipeToTheLeft= delta > 0
    }

    private val _dragState = MutableLiveData<DraggableState>(draggableState)
    val dragState:LiveData<DraggableState> = _dragState

    fun updateTabIndexBasedOnSwipe(){
        _tabIndex.value = when (!isSwipeToTheLeft){
            // I had to invert the value so it would swipe in the right direction
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int){
        _tabIndex.value = i
    }

    // TODO: this should get all the songs, genres, artists, etc. from Library
}