package com.shashank.android_assignment_shashank.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shashank.android_assignment_shashank.data.local.Task
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.allTasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Task Manager",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { innerPadding ->
            // Main container with scaffold padding applied
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // New Task Input Row
                var newTaskTitle by remember { mutableStateOf("") }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = newTaskTitle,
                        onValueChange = { newTaskTitle = it },
                        label = { Text("New Task") },
                        modifier = Modifier.weight(3f)
                    )
                    GradientButton(
                        text = "Add",
                        modifier = Modifier.weight(1f),
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFF4CAF50), Color(0xFF81C784))
                        ),
                        onClick = {
                            if (newTaskTitle.isNotEmpty()) {
                                viewModel.addTask(newTaskTitle)
                                newTaskTitle = ""
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Task list occupies the remaining space and scrolls if needed.
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(tasks) { task ->
                        TaskItem(
                            task = task,
                            onToggle = { isChecked ->
                                viewModel.updateTaskByComplete(task.copy(isCompleted = isChecked))
                            },
                            onDelete = { viewModel.deleteTask(task) },
                            onEdit = { editedText, isChecked ->
                                viewModel.updateTaskByEdit(task.copy(title = editedText, isCompleted = isChecked))
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Debug Buttons for Crash Simulation at the bottom
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    GradientButton(
                        text = "Crash App",
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFFE53935), Color(0xFFEF5350))
                        ),
                        onClick = { throw RuntimeException("Crash to test Firebase Crashlytics") },
                        modifier = Modifier.weight(1f)
                    )
                    GradientButton(
                        text = "Crash DB",
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFFE53935), Color(0xFFEF5350))
                        ),
                        onClick = { viewModel.simulateDbCrash() },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    )
}

/**
 * A custom gradient button composable that uses a Box with a gradient background,
 * rounded corners, and clickable behavior.
 */
@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
            .background(brush = gradient, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp)
        )
    }
}


