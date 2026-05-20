package com.example.valentinesgarage.gui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valentinesgarage.data.entities.Task
import com.example.valentinesgarage.data.entities.Truck
import com.example.valentinesgarage.viewmodel.TaskViewModel
import com.example.valentinesgarage.data.local.dao.TaskDao

@Composable
fun TruckDetailsScreen(
    truck: Truck,
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit
) {

    val tasks by taskViewModel
        .getTasks(truck.id)
        .collectAsState(initial = emptyList())

    var taskTitle by remember {
        mutableStateOf("")
    }

    var taskNotes by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Button(onClick = onBackClick) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = truck.truckName,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Owner: ${truck.ownerName}")
        Text("Kilometers: ${truck.kilometers}")
        Text("Issue: ${truck.issue}")
        Text("Condition: ${truck.condition}")

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Add Repair Task",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = taskTitle,
            onValueChange = {
                taskTitle = it
            },
            label = {
                Text("Task Title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = taskNotes,
            onValueChange = {
                taskNotes = it
            },
            label = {
                Text("Task Notes")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                val task = Task(
                    truckId = truck.id,
                    taskName = taskTitle,
                    notes = taskNotes,
                    isDone = false
                )

                taskViewModel.addTask(task)

                taskTitle = ""
                taskNotes = ""
            }
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Repair Tasks",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {

            items(tasks) { task ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(task.taskName)

                        if (task.notes.isNotBlank()) {
                            Text(task.notes)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {

                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = { checked ->

                                    val updatedTask =
                                        task.copy(isDone = checked)

                                    taskViewModel.updateTask(updatedTask)
                                }
                            )

                            Text(
                                if (task.isDone)
                                    "Completed"
                                else
                                    "Pending"
                            )
                        }
                    }
                }
            }
        }
    }
}