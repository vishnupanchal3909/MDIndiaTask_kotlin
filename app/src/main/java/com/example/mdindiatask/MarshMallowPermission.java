package com.example.mdindiatask;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MarshMallowPermission {
    public static final int STARTUP_PERMISSION_REQUEST_CODE = 13;
    Activity activity;

    public MarshMallowPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean hasStartupPermission() {
        String[] permissions = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions = new String[]{Manifest.permission.INTERNET};
        } else {
            permissions = new String[]{Manifest.permission.INTERNET};
        }
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public boolean checkPermissionForstartUp() {

        int a = ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (a != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        return true;
    }


}
