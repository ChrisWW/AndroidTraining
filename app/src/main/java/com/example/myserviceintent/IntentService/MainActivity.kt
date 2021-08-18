package com.example.myserviceintent.IntentService

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myserviceintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val br = BroadcastExample()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var counter = 0

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
            Toast.makeText(this, "StopService", Toast.LENGTH_SHORT).show()
    }

        binding.bt1.setOnClickListener({

            counter += 1
            binding.tx1.setText("" + counter)


        })


        binding.bt2.setOnClickListener {

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)


        }

    }

    override fun onStart() {
        super.onStart()

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        this.registerReceiver(br, filter)
    }

    override fun onStop() {
        super.onStop()
        this.unregisterReceiver(br)
    }




}