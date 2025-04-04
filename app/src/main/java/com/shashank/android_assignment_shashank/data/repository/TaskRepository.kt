package com.shashank.android_assignment_shashank.data.repository

import android.util.Log
import com.shashank.android_assignment_shashank.data.local.Task
import com.shashank.android_assignment_shashank.data.local.TaskDao
import com.shashank.android_assignment_shashank.data.remote.TaskApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class TaskRepository(
    private val taskDao: TaskDao,
    private val taskApi: TaskApiService
) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun fetchTasksFromApi() {
        try {
            val localTasks = taskDao.getAllTasks().firstOrNull() // Fetch from Room first

            if (localTasks.isNullOrEmpty()) { // If database is empty, fetch from API
                val tasksFromApi = taskApi.getTasks().take(10).map { it.toTask() }
                 tasksFromApi.forEach { task ->
                    taskDao.insertTask(task) // Save first 10 tasks in DB

                }
                 Log.d("TaskRepository", "Fetched tasks from API and saved in DB")
            } else {
                Log.d("TaskRepository", "Using tasks from Room Database")
            }

        } catch (e: Exception) {
            Log.e("TaskRepository", "Error fetching tasks", e)
        }
    }


     suspend fun addTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    fun simulateDbCrash() {
        // Deliberately access null or illegal data
        val task: Task? = null
        val title = task!!.title // Will crash with NullPointerException
    }

}
