package com.learn.photo

import android.net.Uri

/**
 *
 * @author huangwei
 * @date 2020/12/24 18:21
 * @desc
 */
class TakePhotoCall(
    private val takePhotoBuilder: TakePhotoBuilder,
    private val fragment: PhotoFragment
) {

    fun apply(block: (Uri?, List<Uri>?) -> Unit) {
        fragment.apply(takePhotoBuilder,block)
    }
}