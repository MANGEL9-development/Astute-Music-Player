package com.mangel9development.astutemusicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mangel9development.astutemusicplayer.ui.theme.AstuteMusicPlayerTheme

class MainActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstuteMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier=Modifier.fillMaxSize(),
                    color=MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name:String, modifier:Modifier=Modifier) {
    Text(
        text="Hello $name!",
        modifier=modifier
    )
}

@Preview(showBackground=true)
@Composable
fun GreetingPreview() {
    AstuteMusicPlayerTheme {
        Greeting("Android")
    }
}