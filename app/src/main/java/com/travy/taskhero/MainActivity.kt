package com.travy.taskhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.travy.taskhero.ui.theme.TaskHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskHeroTheme {
                TaskHeroApp()
            }
        }
    }
}

@Composable
fun TaskHeroApp() {
    var tasks by remember { mutableStateOf(listOf<String>()) }
    var showAddScreen by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<String?>(null) }

    when {
        showAddScreen -> AddTaskScreen(
            onSave = { newTask ->
                tasks = tasks + newTask
                showAddScreen = false
            },
            onCancel = { showAddScreen = false }
        )
        selectedTask != null -> TaskDetailScreen(
            task = selectedTask!!,
            onBack = { selectedTask = null }
        )
        else -> MainScreen(
            tasks = tasks,
            onAddClick = { showAddScreen = true },
            onTaskClick = { selectedTask = it }
        )
    }
}
