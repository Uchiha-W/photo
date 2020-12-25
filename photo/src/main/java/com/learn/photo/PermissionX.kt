package com.learn.photo

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 *
 * @author huangwei
 * @date 2020/12/25 11:25
 * @desc 权限管理类
 */
class PermissionX {
    companion object {
        private const val permission = android.Manifest.permission.CAMERA
        fun hasPermission(context: Fragment): Boolean {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                return true
            }
            return context.shouldShowRequestPermissionRationale(permission)

        }

        fun requestPermission(fragment: Fragment, code: Int) {
            fragment.requestPermissions(arrayOf(permission),code)
        }
    }
}