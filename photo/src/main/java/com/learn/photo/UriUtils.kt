package com.learn.photo

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

/**
 *
 * @author huangwei
 * @date 2020/11/16 16:34
 * @desc
 */
class UriUtils {
    companion object {

        fun file2Path(mFile: File, mContext: Context): Uri {
            val mUri: Uri?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mUri = FileProvider.getUriForFile(
                    mContext,
                    mContext.applicationInfo?.packageName + ".provider",
                    mFile
                )
            } else {
                mUri = Uri.fromFile(mFile)
            }
            return mUri
        }
    }
}