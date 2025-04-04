package com.shashank.android_assignment_shashank.data.local

 import androidx.room.Entity
 import androidx.room.PrimaryKey
 import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    @SerializedName("completed") val isCompleted: Boolean = false
)

