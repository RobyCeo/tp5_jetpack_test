package com.example.jetpack_test_git_robertoimport androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Column {
        Text(
            "Menu",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        Divider()
        listOf(
            "Afficher une liste" to "exercises",
            "Ajouter un input et garder dans l'historique" to "training_plan",
            "Historique des inputs" to "history",
            "Affichage de video" to "videos",
            "Sauvegarde PDF" to "save_pdf",
            "Lecteur de Son" to "sound_player",
            "Test Animation" to "test_animation"
        ).forEach { (title, route) ->
            TextButton(onClick = {
                scope.launch {
                    navController.navigate(route)
                    drawerState.close()
                }
            }) {
                Text(title)
            }
        }
    }
}


