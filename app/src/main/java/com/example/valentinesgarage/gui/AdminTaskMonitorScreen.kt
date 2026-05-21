package com.example.valentinesgarage.gui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valentinesgarage.viewmodel.TaskViewModel

@Composable
fun AdminTaskMonitorScreen(
    taskViewModel: TaskViewModel
) {

    val tasks by taskViewModel.allTasks
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Employee Task Monitor",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(tasks) { task ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text("Task: ${task.taskName}")

                        Text(
                            "Status: ${
                                if (task.isDone)
                                    "Completed"
                                else
                                    "Pending"
                            }"
                        )

                        Text("Truck ID: ${task.truckId}")

                        if (task.notes.isNotBlank()) {
                            Text("Notes: ${task.notes}")
                        }
                    }
                }
            }
        }
    }
}