package com.example.valentinesgarage.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valentinesgarage.data.entities.Truck
import com.example.valentinesgarage.viewmodel.TruckViewModel

@Composable
fun TruckScreen(
    truckViewModel: TruckViewModel,
    onTruckClick: (Truck) -> Unit
) {

    val trucks by truckViewModel.allTrucks.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "All Trucks in Garage",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(trucks) { truck ->

                TruckItem(
                    truck = truck,
                    onClick = {
                        onTruckClick(truck)
                    }
                )
            }
        }
    }
}

@Composable
fun TruckItem(
    truck: Truck,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text("Truck: ${truck.truckName}")
            Text("Owner: ${truck.ownerName}")
            Text("KM: ${truck.kilometers}")
            Text("Issue: ${truck.issue}")
            Text("Condition: ${truck.condition}")
        }
    }
}