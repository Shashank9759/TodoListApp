package com.shashank.android_assignment_shashank.presentation

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.shashank.android_assignment_shashank.data.local.Task

@Composable
fun TaskScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.allTasks.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(), // Fill entire screen
        contentAlignment = Alignment.Center // Center content in the Box
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center items in Column
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Task Manager",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            var newTaskTitle by remember { mutableStateOf("") }

            Row(
                verticalAlignment = Alignment.CenterVertically, // Align items in Row
                horizontalArrangement = Arrangement.Center, // Center Row content
                modifier = Modifier.fillMaxWidth() // Ensure full width for centering
            ) {
                TextField(
                    value = newTaskTitle,
                    onValueChange = { newTaskTitle = it },
                    label = { Text("New Task") },
                    modifier = Modifier.weight(1f) // Make TextField take space
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (newTaskTitle.isNotEmpty()) {
                        viewModel.addTask(newTaskTitle)
                        newTaskTitle = ""
                    }
                }) {
                    Text("Add")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Center list items
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task,
//                        onToggle = { viewModel.updateTask(task) },
                        onToggle = { isChecked ->

                            viewModel.updateTaskByComplete(task.copy(isCompleted = isChecked))

                        },

                        onDelete = {
                            viewModel.deleteTask(task)

                        },
                        onEdit = { editedText, isChecked ->
                            viewModel.updateTaskByEdit(
                                task.copy(
                                    title = editedText,
                                    isCompleted = isChecked
                                )
                            )
//                            Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }


            Button(onClick = {
                throw RuntimeException("Crash to test Firebase Crashlytics")
            }) {
                Text("Crash App")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.simulateDbCrash()
            }) {
                Text("Crash DB")
            }

        }




    }
//    if (tasks.isLoading) {
//        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//    }

}
