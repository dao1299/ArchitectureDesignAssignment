package com.example.recyclerviewmvvmstudy.service;

import static com.example.recyclerviewmvvmstudy.application.MyApplication.NOTIFICATION_CHANNEL_ID;

import android.app.Notification;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.recyclerviewmvvmstudy.R;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ServiceForeground extends Service {
    private static final String TAG = "ServiceForeground";

    private Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(this::getPackageNameCurrent).start();
        initNotification("123");
        return START_STICKY;
    }


    private void getPackageNameCurrent() {


        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> appList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 100000, time);
        if (appList != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : appList) {
                mySortedMap.put(usageStats.getLastTimeUsed(),
                        usageStats);
            }
            if (mySortedMap != null && !mySortedMap.isEmpty()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initNotification(mySortedMap.get(
                                mySortedMap.lastKey()).getPackageName());
                    }
                });
            }
        }

        handler.postDelayed(this::getPackageNameCurrent,1000);
    }


    public void initNotification(String packageName){

        Notification notification = new NotificationCompat.Builder(getApplication(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Running app: "+packageName)
                .setSound(null)
                .build();

        startForeground(1, notification);
    }
}
