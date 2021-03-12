package com.example.myserviceintent.IntentService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myserviceintent.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnStartService.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
            startService(it)
            tvServiceInfo.text = "Service runnning"
            }

        }

        btnStopService.setOnClickListener {
            MyIntentService.stopService()
            tvServiceInfo.text = "Service stopped"
    }


    }




}