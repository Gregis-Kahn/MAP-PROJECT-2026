package com.example.valentinesgarage.gui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valentinesgarage.data.entities.Truck
import com.example.valentinesgarage.viewmodel.TruckViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.clickable
import com.example.valentinesgarage.data.entities.Task
import com.example.valentinesgarage.viewmodel.TaskViewModel
import com.example.valentinesgarage.gui.ProfileScreen


@Composable
fun AdminReportScreen(
    truckViewModel: TruckViewModel,
    taskViewModel: TaskViewModel
) {
    val trucks by truckViewModel.allTrucks.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            "Garage Reports",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(trucks) { truck ->

                val tasks by taskViewModel.getTasks(truck.id)
                    .collectAsState(initial = emptyList())

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Text("Truck: ${truck.truckName}")
                        Text("Owner: ${truck.ownerName}")
                        Text("Condition: ${truck.condition}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Tasks Completed:")

                        val doneCount = tasks.count { it.isDone }
                        Text("$doneCount / ${tasks.size}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Recent Notes:")

                        tasks.forEach { task ->
                            if (task.notes.isNotBlank()) {
                                Text("- ${task.notes}")
                            }
                        }
                    }
                }
            }
        }
    }
}