package com.example.myserviceintent.IntentService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myserviceintent.R
import com.example.myserviceintent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStartService.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
            startService(it)
            binding.tvServiceInfo.text = "Service runnning"
                Toast.makeText(this, "StartService", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnStopService.setOnClickListener {
            MyIntentService.stopService()
            binding.tvServiceInfo.text = "Service stopped"
            Toast.makeText(this, "StopService",Toast.LENGTH_SHORT).show()
    }
    }




}