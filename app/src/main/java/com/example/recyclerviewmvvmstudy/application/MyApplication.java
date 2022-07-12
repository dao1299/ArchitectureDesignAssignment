package com.example.recyclerviewmvvmstudy.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";
    public void onCreate() {
        super.onCreate();
        createNotificationChanel();
    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Notification Service",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setSound(null,null);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager!=null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
