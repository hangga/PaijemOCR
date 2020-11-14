package id.co.paijemocr.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.paijemocr.BuildConfig;

public class Utils {
    public static boolean isHasPermissions(Context context, String[] permissions,
                                           OnCheckPermission onCheckPermission) {
        List<String> permissionNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(permission);
                Utils.log("UN-GRANTED:" + permission);
            }
        }
        Utils.log("UN-GRANTED:" + permissionNeeded.size());
        onCheckPermission.onPermissionNeeded(permissionNeeded.toArray(new String[0]));

        return (permissionNeeded.size() == 0);
    }

    public static File createImageFile(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        mFile.mkdirs();
        return mFile;
    }

    public static void log(String str) {
        if (BuildConfig.DEBUG) {
            Log.v("OCR", str);
        }
    }
}
