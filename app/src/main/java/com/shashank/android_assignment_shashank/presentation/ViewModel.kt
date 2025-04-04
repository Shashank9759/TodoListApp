package com.shashank.android_assignment_shashank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.shashank.android_assignment_shashank.data.local.Task
import com.shashank.android_assignment_shashank.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    val allTasks: StateFlow<List<Task>> = repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )
    init {
        viewModelScope.launch {
            repository.fetchTasksFromApi()
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(Task(title = title))
            Firebase.analytics.logEvent("task_added") {
                param("title", title)
            }
        }
    }

    fun updateTaskByComplete(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = task.isCompleted))
            Firebase.analytics.logEvent("task_completed") {
                param("title", task.title)
            }
        }
    }
    fun updateTaskByEdit(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = task.isCompleted, title = task.title))

            Firebase.analytics.logEvent("task_edited") {
                param("title", task.title)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            Firebase.analytics.logEvent("task_deleted") {
                param("title", task.title)
            }
        }
    }

    fun simulateDbCrash() {
        viewModelScope.launch {
            repository.simulateDbCrash()
        }
    }
}
