package com.example.myserviceintent.IntentService

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myserviceintent.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val br = BroadcastExample()
    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"
    private lateinit var binding: ActivityMainBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }

        binding.btnStartService.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                binding.tvServiceInfo.text = "Service runnning"
                Toast.makeText(this, "StartService", Toast.LENGTH_SHORT).show()
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
    }

    private suspend fun fakeApiRequest() {
        val result1 = getResult1FromApi()
        println("debug: $result1")
        setTextOnMainThread(result1)

        val result2 = getResult2FromApi(result1)
        setTextOnMainThread(result2)
    }

    private fun changeText(input: String) {
        val changeText = binding.tx1.text.toString() + "\n$input"
        binding.tx1.text = changeText
    }

    private suspend fun setTextOnMainThread(input: String) {
        withContext(Main) {
            delay(10000)
            changeText(input)
        }
    }

    private suspend fun getResult2FromApi(result1: String): String {
        logThread("getResult2FromApi")
        delay(1000)
        return RESULT_2
    }

    private suspend fun getResult1FromApi(): String {
        logThread("getResultFromApi")
        delay(1000)
        return RESULT_1

    }

    private fun logThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")

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