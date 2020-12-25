package com.learn.photo

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat

/**
 *
 * @author huangwei
 * @date 2020/12/25 9:59
 * @desc
 */
class FileUtils {

    companion object {
        private val fileRootPath = File.separator + "img"

        private fun getPhotoPath(context: Context): String {
            return context.filesDir.absolutePath + fileRootPath + SimpleDateFormat.getDateInstance() + ".dpg"
        }

        fun getPhotoFile(context: Context) =
            File(getPhotoPath(context)).apply {
                mkdirs()
                if (exists()) {
                    delete()
                }
            }
    }
}