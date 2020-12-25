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

    fun requestPermission(
        permissionFailure: () -> Unit
    ): TakePhotoBuilder {
        this.permissionFailure = permissionFailure
        shouldRequestPermission = true
        return this
    }


    fun setTakePhoto(): TakePhotoCall {
        type = Constant.take_photo
        return TakePhotoCall(this,mContext)
    }

    fun setOpenAlbum(multi: Boolean = false): TakePhotoCall {
        type = Constant.album
        return TakePhotoCall(this,mContext)
    }

    fun setOpenDoc(multi: Boolean = false): TakePhotoCall {
        type = Constant.doc
        return TakePhotoCall(this,mContext)
    }

}