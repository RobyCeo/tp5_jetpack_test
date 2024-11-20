package com.example.jetpack_test_git_robertoimport androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ExerciseListScreen() {
    val exercises = listOf(
        "Bench Press" to "Construit un plus gros chest!",
        "Squat" to "Exercise bon pour les jambes",
        "Deadlift" to "Améliore la force de tout le buste"
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(exercises) { exercise ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)

            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = exercise.first,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = exercise.second,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
