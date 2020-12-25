package com.demo.photo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learn.photo.Photo
import com.learn.photo.R

/**
 *
 * @author huangwei
 * @date 2020/12/25 11:40
 * @desc
 */
class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        Photo.with(this).requestPermission {}.setTakePhoto().apply { i0, i1 ->
        }
    }
}