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
    val tasks = remember { mutableStateListOf<String>() } // mutable list

    var showAddScreen by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<String?>(null) }

    when {
        showAddScreen -> AddTaskScreen(
            onSave = { newTask ->
                tasks.add(newTask) // langsung bisa add
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
            onTaskClick = { task -> selectedTask = task },
            onDeleteClick = { task -> tasks.remove(task) } // sekarang bisa
        )
    }
}
