package com.example.jetpack_test_git_roberto

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val history = remember { mutableStateListOf<String>() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box {
        ModalNavigationDrawer(
            drawerContent = {
                DrawerContent(
                    navController = navController,
                    drawerState = drawerState
                )
            },
            drawerState = drawerState,
            scrimColor = Color.Black.copy(alpha = 1f)
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Application d'entraînement") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close()
                                        } else {
                                            drawerState.open()
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = "Menu",
                                    tint = if (drawerState.isOpen) Color.Green else MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    )
                },
                content = { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("home") { HomeScreen() }
                        composable("exercises") { ExerciseListScreen() }
                        composable("input") { InputScreen() }
                        composable("training_plan") {
                            TrainingPlanScreen { history.add(it) }
                        }
                        composable("history") { ExerciseHistoryScreen(history) }
                        composable("videos") { VideoPlayerScreen() }
                        composable("save_pdf") { SavePdfScreen(history) }
                        composable("sound_player") { SoundPlayerScreen() }
                        composable("test_animation") { TestAnimationScreen() }
                    }
                }
            )
        }

        if (drawerState.isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu",
                        tint = Color.Green
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ROBYKOUT",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}






