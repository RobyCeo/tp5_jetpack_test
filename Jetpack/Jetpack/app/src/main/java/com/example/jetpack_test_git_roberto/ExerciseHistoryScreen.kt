package com.example.jetpack_test_git_roberto
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ExerciseHistoryScreen(history: List<String>) {
    LazyColumn {
        items(history) { item ->
            Text(item, modifier = Modifier.padding(8.dp))
        }
    }
}

