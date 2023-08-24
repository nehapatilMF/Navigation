package com.example.navigation.MyWorkObject

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.navigation.workmanager.MyWorker
import java.util.concurrent.TimeUnit

object MyWorkObject {
    fun myOneTimeWork(context: Context) {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()
        val myWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(context).enqueue(myWorkRequest)
    }

    fun myPeriodicWork(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()
        val myRequest = PeriodicWorkRequest.Builder(MyWorker::class.java,
            15,
            TimeUnit.MINUTES,
        ).setConstraints(constraints)
            .addTag("my_id")
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "my_id",
                ExistingPeriodicWorkPolicy.KEEP,
                myRequest)
    }
}

