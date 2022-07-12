package com.example.recyclerviewmvvmstudy.viewmodel;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;

import com.example.recyclerviewmvvmstudy.adapter.ListAdapter;
import com.example.recyclerviewmvvmstudy.databinding.ActivityMainBinding;
import com.example.recyclerviewmvvmstudy.model.DataModel;
import com.example.recyclerviewmvvmstudy.util.ImageUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataViewModel extends BaseObservable {
    List<DataModel> dataModelList = new ArrayList<>();
    Context context;

    public DataViewModel(ActivityMainBinding binding, Context context) {
        this.context = context;
        getData();
        ListAdapter listAdapter = new ListAdapter(dataModelList);
        binding.setAdapterList(listAdapter);
    }

    private void getData() {
        if (checkUsageStatsPermission()) {
            solution();
        } else {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            context.startActivity(intent);
        }
    }

    private boolean checkUsageStatsPermission() {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppCompatActivity.APP_OPS_SERVICE);
        int mode;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mode = appOpsManager.unsafeCheckOpRawNoThrow(
                    "android:get_usage_stats",
                    Process.myUid(),
                    context.getPackageName()
            );
        } else {
            mode = appOpsManager.checkOpNoThrow(
                    "android:get_usage_stats",
                    Process.myUid(),
                    context.getPackageName()
            );
        }
        return (mode == AppOpsManager.MODE_ALLOWED);
    }

    private void solution() {
        String packageName;
        long totalTime;
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 100000, time);
        if (stats != null) {
            for (UsageStats x : stats) {
                totalTime = x.getTotalTimeInForeground();
                packageName = x.getPackageName();
                Drawable icon = new ImageUtil().getAppIconByPackageName(context, packageName);
                String timeApp = convertTimeToString(totalTime);
                dataModelList.add(new DataModel(packageName,timeApp,icon,totalTime));
            }
        }
        Collections.sort(dataModelList);
    }

    private String convertTimeToString(long time) {
        return ((time / (1000 * 60 * 60)) % 24) + "h:" + ((time / (1000 * 60)) % 60) + "m:" + ((time / 1000) % 60)+"s";
    }
}
