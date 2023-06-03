package com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.navigation.NavigationGraph
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.parts.BottomBar
import com.mangel9development.astutemusicplayer.ui.theme.AstuteMusicPlayerTheme

class PlayerScreensActivity:ComponentActivity() {
    @OptIn(
        ExperimentalComposeUiApi::class,
        ExperimentalFoundationApi::class,
        ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstuteMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier=Modifier.fillMaxSize(),
                    color=MaterialTheme.colorScheme.background
                ) {
                    PlayerScreens()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun PlayerScreens() {
    val navController = rememberNavController()
    Scaffold(
        /* Top bars will be included in each screen
        topBar = {
            TopBar()
        },*/
        bottomBar = {
            BottomBar(navController)
        }
    ){
        NavigationGraph(navController)
    }
}