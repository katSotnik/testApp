package com.dev.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.testapp.data.models.NotificationModel
import com.dev.testapp.extensions.parcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}