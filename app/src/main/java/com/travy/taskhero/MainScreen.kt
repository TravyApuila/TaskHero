package com.travy.taskhero

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    tasks: List<String>,
    onAddClick: () -> Unit,
    onTaskClick: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("🦸‍♂️ TaskHero", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (tasks.isEmpty()) {
                Text("Belum ada tugas.")
            } else {
                LazyColumn {
                    items(tasks) { task ->
                        Text(
                            text = task,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onTaskClick(task) }
                                .padding(8.dp)
                        )
                        Divider()
                    }
                }
            }
        }
    }
}
