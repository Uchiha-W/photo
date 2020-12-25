package com.learn.photo

import android.net.Uri
import androidx.fragment.app.FragmentActivity

/**
 *
 * @author huangwei
 * @date 2020/12/24 18:21
 * @desc
 */
class TakePhotoCall(
    private val takePhotoBuilder: TakePhotoBuilder, private val activity: FragmentActivity
) {
    private val default = "DEFAULT"

    fun apply(block: (Uri?, List<Uri>?) -> Unit) {
        var photoFragment: PhotoFragment? = null
        if (activity.supportFragmentManager.findFragmentByTag(default) != null) {
            photoFragment =
                activity.supportFragmentManager.findFragmentByTag(default) as PhotoFragment
        } else {
            photoFragment = PhotoFragment.newInstance()
            activity.supportFragmentManager.beginTransaction()
                .add(photoFragment, default).commitNow()
        }
        photoFragment.apply(takePhotoBuilder, block)
    }
}