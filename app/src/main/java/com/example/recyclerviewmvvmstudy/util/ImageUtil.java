package com.example.recyclerviewmvvmstudy.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.recyclerviewmvvmstudy.R;


public class ImageUtil{
    public Drawable getAppIconByPackageName(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return context.getDrawable(R.drawable.ic_launcher_foreground);
    }
}
