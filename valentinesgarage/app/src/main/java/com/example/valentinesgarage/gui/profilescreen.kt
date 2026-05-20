package com.example.valentinesgarage.gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valentinesgarage.viewmodel.TruckViewModel
import com.example.valentinesgarage.data.entities.User

@Composable
fun ProfileScreen(
    user: User,
    truckViewModel: TruckViewModel
) {

    val trucks by truckViewModel.allTrucks.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Name: ${user.fullName}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Email: ${user.email}"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Role: ${user.role}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (user.role == "Admin") {

                    Text("Admin Dashboard Access")
                    Text("Garage Trucks: ${trucks.size}")

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Permissions",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text("• Manage Trucks")
                    Text("• View Reports")
                    Text("• Monitor Tasks")
                    Text("• Manage Employees")

                } else {

                    val fixedCars =
                        trucks.count { it.condition == "Fixed" }

                    Text("Mechanic Account")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Cars Worked On: ${trucks.size}")
                    Text("Cars Fixed: $fixedCars")

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Employee Access",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text("• View Assigned Cars")
                    Text("• Update Repairs")
                    Text("• Check In Vehicles")
                }
            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // logout later
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}