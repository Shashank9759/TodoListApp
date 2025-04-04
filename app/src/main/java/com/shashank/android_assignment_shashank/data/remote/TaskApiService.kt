package com.shashank.android_assignment_shashank.data.remote

import com.shashank.android_assignment_shashank.data.remote.TaskDto
import retrofit2.http.GET

interface TaskApiService {
    @GET("todos")
    suspend fun getTasks(): List<TaskDto> // Use TaskDto instead of Task
}
