package com.example.jetpack_test_git_roberto

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SoundPlayerScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val mediaPlayer = MediaPlayer.create(context, R.raw.progression)
            mediaPlayer.start()
        }) {
            Text("Play Progression")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val mediaPlayer = MediaPlayer.create(context, R.raw.regression)
            mediaPlayer.start()
        }) {
            Text("Play Regression")
        }
    }
}
