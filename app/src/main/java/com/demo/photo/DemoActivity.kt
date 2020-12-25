package com.demo.photo

import android.os.Bundle
import android.widget.Button
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
        findViewById<Button>(R.id.btn).setOnClickListener {
            Photo.with(this).requestPermission {}.setTakePhoto().apply { i0, i1 ->
            }
        }
    }
}