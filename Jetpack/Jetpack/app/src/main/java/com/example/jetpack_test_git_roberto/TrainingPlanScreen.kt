package com.example.jetpack_test_git_roberto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TrainingPlanScreen(onAdd: (String) -> Unit) {
    var exercise by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = exercise,
            onValueChange = { exercise = it },
            label = { Text("Ajouter un exercise") }
        )
        Button(onClick = { onAdd(exercise) }) {
            Text("Ajouter l'exercise dans l'historique")
        }
    }
}
