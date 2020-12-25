package com.learn.photo

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.Fragment

/**
 *
 * @author huangwei
 * @date 2020/12/24 17:21
 * @desc
 */
class PhotoFragment : Fragment() {
    private val cameraCode = 1024
    lateinit var takePhotoUri: Uri
    private var onPermissionFailure: (() -> Unit)? = null
    private lateinit var onCallBack: (Uri?, List<Uri>?) -> Unit
    private lateinit var takePhotoBuilder: TakePhotoBuilder

    companion object {
        fun newInstance() = PhotoFragment()
    }


    fun apply(takePhotoBuilder: TakePhotoBuilder, onCallBack: (Uri?, List<Uri>?) -> Unit) {
        this.takePhotoBuilder = takePhotoBuilder
        this.onCallBack = onCallBack
        requestCameraPermission { takePhotoBuilder.permissionFailure?.invoke() }
    }

    private fun requestCameraPermission(permissionFailure: () -> Unit) {
        this.onPermissionFailure = permissionFailure
        if (PermissionX.hasPermission(activity!!)) {
            apply()
        } else {
            PermissionX.requestPermission(activity!!, cameraCode)
        }
    }

    private fun apply() {
        when (takePhotoBuilder.type) {
            Constant.take_photo -> takePhoto()
            Constant.album -> openAlbum()
            Constant.doc -> openDoc()
            else -> takePhoto()
        }
    }

    private fun takePhoto() {
        activity?.let {
            if (it.isDestroyed) {
                return
            }
            if (PermissionX.hasPermission(it)) {
                val file = FileUtils.getPhotoFile(it)
                takePhotoUri = UriUtils.file2Path(file, it)
                IntentUtil.takePhoto(this, takePhotoUri)
            } else {
                return
            }
        }
    }

    private fun openAlbum(multi: Boolean = false) {
        IntentUtil.openAlbum(this, multi)
    }

    private fun openDoc(multi: Boolean = false) {
        IntentUtil.openDoc(this, multi)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            apply()
        } else {
            onPermissionFailure?.invoke()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.take_photo && resultCode == Activity.RESULT_OK) {
            onCallBack.invoke(takePhotoUri, null)
        } else if ((requestCode == Constant.album || requestCode == Constant.doc) && resultCode == Activity.RESULT_OK) {
            data ?: return
            data.clipData?.let {
                if (it.itemCount == 1) {
                    onCallBack.invoke(it.getItemAt(0).uri, null)
                } else {
                    val mUriList = mutableListOf<Uri>()
                    for (i in 0 until it.itemCount) {
                        mUriList.add(it.getItemAt(i).uri)
                    }
                    onCallBack.invoke(null, mUriList)
                }
                return
            }
            data.data?.let {
                onCallBack.invoke(it, null)
            }
        } else if (requestCode == Constant.custom && resultCode == Activity.RESULT_OK) {
            data ?: return
            val mUri: Uri? = data.getParcelableExtra("mUri")
            val mUriList: List<Uri>? = data.getParcelableArrayListExtra("mUriList")
            onCallBack.invoke(mUri, mUriList)
        }
    }
}