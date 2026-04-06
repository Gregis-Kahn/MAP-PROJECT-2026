package com.example.valentinesgarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.valentinesgarage.ui.theme.ValentinesgarageTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ValentinesgarageTheme() {
                ValentineApp()
            }
        }
    }
}

enum class AuthScreen {
    LOGIN,
    SIGNUP
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Cars", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountCircle),
}

@Composable
fun ValentineApp() {

    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
    var authScreen by rememberSaveable { mutableStateOf(AuthScreen.LOGIN) }
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var userRole by rememberSaveable { mutableStateOf("Employee") }
    var showAddCarScreen by rememberSaveable { mutableStateOf(false) }

    if (!isLoggedIn) {
        when (authScreen) {
            AuthScreen.LOGIN -> LoginScreen(
                onLoginClick = { isLoggedIn = true },
                onSignupClick = { authScreen = AuthScreen.SIGNUP }
            )

            AuthScreen.SIGNUP -> SignupScreen(
                onSignupComplete = { role ->
                    userRole = role
                    authScreen = AuthScreen.LOGIN
                }
            )
        }
    } else {

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.label
                            )
                        },
                        label = { Text(it.label) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it }
                    )
                }
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    when (currentDestination) {
                        AppDestinations.HOME -> {
                            if (showAddCarScreen) {
                                AddCarScreen(
                                    onSave = {
                                        showAddCarScreen = false
                                    },
                                    onCancel = {
                                        showAddCarScreen = false
                                    }
                                )
                            } else {
                                HomeScreen(
                                    userRole,
                                    onAddCarClick = { showAddCarScreen = true }
                                )
                            }
                        }

                        AppDestinations.FAVORITES -> CarsScreen(userRole)
                        AppDestinations.PROFILE -> ProfileScreen(userRole)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Valentines Garage", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onLoginClick) {
            Text("Login")
        }

        TextButton(onClick = onSignupClick) {
            Text("Don't have an account? Sign up")
        }
    }
}

@Composable
fun SignupScreen(
    onSignupComplete: (String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var role by rememberSaveable { mutableStateOf("Employee") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
 
        Text("Create Account", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value= password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Select Role")

        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = role == "Employee",
                    onClick = { role = "Employee" }
                )
                Text("Mechanic")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = role == "Admin",
                    onClick = { role = "Admin" }
                )
                Text("Admin")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onSignupComplete(role) }) {
            Text("Sign Up")
        }
    }
}

@Composable
fun HomeScreen(
    role: String,
    onAddCarClick: () -> Unit
) {
    when (role) {
        "Admin" -> AdminDashboard()
        else -> EmployeeDashboard(onAddCarClick)
    }
}

@Composable
fun CarsScreen(role: String) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Cars Section")

        if (role == "Admin") {
            Text("View all cars in the garage.")
        } else {
            Text("View cars assigned to you.")
        }
    }
}

@Composable
fun ProfileScreen(role: String) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Profile")
        Text("Role: $role")
    }
}

@Composable
fun AdminDashboard() {

    Column(modifier = Modifier.padding(24.dp)) {

        Text("Admin Dashboard", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Company Overview")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Total Mechanics: 5")
        Text("Cars in Garage: 12")
        Text("Completed Repairs: 8")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Employee Progress:")

        Text("- John: 3 cars fixed")
        Text("- Mike: 2 cars pending")
    }
}

@Composable
fun EmployeeDashboard(
    onAddCarClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text("Employee Dashboard", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Your Checked-in Cars:")

            Spacer(modifier = Modifier.height(8.dp))

            CarItem("Toyota Corolla", "Oil leak", "Pending")
            CarItem("BMW X5", "Brake issue", "Fixed")
        }

        // ✅ BUTTON AT BOTTOM
        Button(
            onClick = onAddCarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Check in New Car")
        }
    }
}

@Composable
fun CarItem(name: String, issue: String, status: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text("Car: $name")
            Text("Issue: $issue")
            Text("Status: $status")
        }
    }
}

@Composable
fun AddCarScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    var carName by rememberSaveable { mutableStateOf("") }
    var ownerName by rememberSaveable { mutableStateOf("") }
    var issue by rememberSaveable { mutableStateOf("") }
    var status by rememberSaveable { mutableStateOf("Pending") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Check in New Car", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = carName,
            onValueChange = { carName = it },
            label = { Text("Car Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ownerName,
            onValueChange = { ownerName = it },
            label = { Text("Owner Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = issue,
            onValueChange = { issue = it },
            label = { Text("Issue") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Status")

        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = status == "Pending",
                    onClick = { status = "Pending" }
                )
                Text("Pending")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = status == "Fixed",
                    onClick = { status = "Fixed" }
                )
                Text("Fixed")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // TODO: Save to database later
                onSave()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}