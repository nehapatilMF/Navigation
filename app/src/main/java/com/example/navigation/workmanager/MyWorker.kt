package com.example.navigation.workmanager

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.navigation.R
import com.example.navigation.activity.MainActivity
import com.example.navigation.fragments.BottomFragment
import com.example.navigation.fragments.Home

class MyWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 1
    }
    override fun doWork(): Result {
        Log.d("success","doWork: Success function called")
        showNotification()
        return Result.success()
    }
    @SuppressLint("MissingPermission")
    private fun showNotification() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.VIBRATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // The permission is granted, proceed to show the notification
           val intent = Intent(applicationContext, BottomFragment::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                0, intent, PendingIntent.FLAG_IMMUTABLE
            )
            val notification = NotificationCompat.Builder(
                applicationContext,
                CHANNEL_ID
            )
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("New Task")
                .setContentText("Subscribe on the channel")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelName = "Channel Name"
                val channelDescription = "Channel Description"
                val channelImportance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, channelName, channelImportance).apply {
                    description = channelDescription
                }

                val notificationManager = applicationContext.getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.createNotificationChannel(channel)
            }
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(NOTIFICATION_ID, notification.build())
            }
        }else{
            Log.e("showNotification", "VIBRATE permission not granted.")
        }
    }
}