package com.learn.photo

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.Fragment

/**
 *
 * @author huangwei
 * @date 2020/11/16 15:21
 * @desc
 */
class IntentUtil {
    companion object {

        //打开文档
        fun openDoc(mContext: Fragment, multiDoc: Boolean = false) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, multiDoc)
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
            mContext.startActivityForResult(
                Intent.createChooser(intent, "Select a File to Upload"),
                Constant.doc
            )
        }

        fun openAlbum(mContext: Fragment, multiDoc: Boolean = false) {
            val intent = Intent(Intent.ACTION_PICK, null)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, multiDoc)
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
            mContext.startActivityForResult(
                intent,
                Constant.album
            )
        }

        //拍照
        fun takePhoto(mContext: Fragment, mUri: Uri) {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
            mContext.startActivityForResult(takePictureIntent, Constant.take_photo)
        }
    }
}