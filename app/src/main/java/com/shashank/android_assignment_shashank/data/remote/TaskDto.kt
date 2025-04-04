package com.shashank.android_assignment_shashank.data.remote


import com.google.gson.annotations.SerializedName
import com.shashank.android_assignment_shashank.data.local.Task

data class TaskDto(
    val id: Int,
    val title: String,
    @SerializedName("completed") val isCompleted: Boolean
) {
    // Convert API TaskDto to Room Task entity
    fun toTask(): Task {
        return Task(id = id, title = title, isCompleted = isCompleted)
    }
}
