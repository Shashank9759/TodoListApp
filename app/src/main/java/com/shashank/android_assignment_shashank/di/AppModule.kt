package com.shashank.android_assignment_shashank.di

import android.content.Context
import androidx.room.Room
import com.shashank.android_assignment_shashank.data.local.TaskDao
import com.shashank.android_assignment_shashank.data.local.TaskDatabase
import com.shashank.android_assignment_shashank.data.remote.TaskApiService
import com.shashank.android_assignment_shashank.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskApiService(): TaskApiService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao, taskApi: TaskApiService): TaskRepository {
        return TaskRepository(taskDao, taskApi) // Now passing taskApi
    }
}
