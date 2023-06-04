package com.mangel9development.astutemusicplayer.ui.activities.loadingActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangel9development.astutemusicplayer.R
import com.mangel9development.astutemusicplayer.model.Library
import com.mangel9development.astutemusicplayer.ui.activities.playerScreensActivity.PlayerScreensActivity
import com.mangel9development.astutemusicplayer.ui.theme.AstuteMusicPlayerTheme
import kotlinx.coroutines.delay

// TODO: watch this video to learn how to implement the animation gradient background:
//  https://www.youtube.com/watch?v=cDnrg75lu3E

class LoadingActivity:ComponentActivity(){
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AstuteMusicPlayerTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier=Modifier.fillMaxSize(),
                    color=MaterialTheme.colorScheme.background
                ) {
                    LoadingScreen{
                        startActivity(Intent(this, PlayerScreensActivity::class.java))
                        finish()
                    }
                }
            }
        }
//        Toast.makeText(this, "Hello toast!", Toast.LENGTH_SHORT).show()
        Library.loadLibrary()
    }
}

@Composable
fun LoadingScreen(onLoaded:()->Unit={}){
    Column(
        verticalArrangement=Arrangement.Center,
        modifier=Modifier.fillMaxSize()
    ){
        Row(
            horizontalArrangement=Arrangement.Center,
            modifier=Modifier.fillMaxWidth()
        ){
            Text(
                stringResource(R.string.app_name),
                fontSize=64.sp,
                lineHeight=64.sp,
                textAlign=TextAlign.Center
            )
        }
        Spacer(modifier=Modifier
            .fillMaxWidth()
            .height(12.dp))
        Row(
            horizontalArrangement=Arrangement.Center,
            modifier=Modifier.fillMaxWidth()
        ){
            Text(
                stringResource(R.string.loading_withDots),
                fontSize=24.sp,
                lineHeight=24.sp
            )
        }
    }
    LaunchedEffect(Unit){
        delay(5000)
        onLoaded()
    }
}

@Preview(showBackground=true)
@Composable
fun GreetingPreview() {
    AstuteMusicPlayerTheme {
        LoadingScreen()
    }
}