package com.learn.photo

import androidx.fragment.app.FragmentActivity

/**
 *
 * @author huangwei
 * @date 2020/12/24 17:44
 * @desc
 */
 class TakePhotoBuilder(private val mContext: FragmentActivity) {

    internal var shouldRequestPermission = true
    internal var type = Constant.take_photo
    internal var permissionFailure: (() -> Unit)? = null
    private lateinit var fragment: PhotoFragment
    private val default = "DEFAULT"

    fun requestPermission(
        permissionFailure: () -> Unit
    ): TakePhotoBuilder {
        return requestPermission(mContext, permissionFailure)
    }


    private fun requestPermission(
        activity: FragmentActivity,
        permissionFailure: () -> Unit
    ): TakePhotoBuilder {
        var photoFragment: PhotoFragment? = null
        if (activity.supportFragmentManager.findFragmentByTag(default) != null) {
            photoFragment =
                activity.supportFragmentManager.findFragmentByTag(default) as PhotoFragment
        } else {
            photoFragment = PhotoFragment.newInstance()
            activity.supportFragmentManager.beginTransaction()
                .add(PhotoFragment.newInstance(), default).commit()
        }
        this.permissionFailure = permissionFailure
        fragment = photoFragment
        shouldRequestPermission = true
        return this
    }

    fun setTakePhoto(): TakePhotoCall {
        type = Constant.take_photo
        return TakePhotoCall(this, fragment)
    }

    fun setOpenAlbum(multi: Boolean = false): TakePhotoCall {
        type = Constant.album
        return TakePhotoCall(this, fragment)
    }

    fun setOpenDoc(multi: Boolean = false): TakePhotoCall {
        type = Constant.doc
        return TakePhotoCall(this, fragment)
    }

}