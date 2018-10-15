package dynamics_permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by kimhillzhang on 2018-01-31.
 */

public class PermissionsChecker {
    private final Context mContext;

    // 把需要动态获取权限的列表放入
   public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }
    public boolean lacksPermissions()
    {
        return  lacksPermissions(PERMISSIONS);
    }

    // 判断权限集合
    private boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }
}
