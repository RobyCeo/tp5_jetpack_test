package com.example.jetpack_test_git_roberto

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun SoundPlayerScreen() {
    val context = LocalContext.current
    Column {
        Button(onClick = {
            val mediaPlayer = MediaPlayer.create(context, R.raw.progression)
            mediaPlayer.start()
        }) {
            Text("Play Progression")
        }
        Button(onClick = {
            val mediaPlayer = MediaPlayer.create(context, R.raw.regression)
            mediaPlayer.start()
        }) {
            Text("Play Regression")
        }
    }
}
