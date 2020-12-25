package com.learn.photo

import androidx.fragment.app.FragmentActivity

/**
 *
 * @author huangwei
 * @date 2020/12/24 17:21
 * @desc
 */
class Photo {
    companion object {
        fun with(mContext: FragmentActivity): TakePhotoBuilder {
            return TakePhotoBuilder(mContext)
        }
    }
}