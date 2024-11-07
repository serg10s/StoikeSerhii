package com.example.counterapp

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button

    private val counterReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val count = intent?.getIntExtra("count_value", 0) ?: 0
            counterTextView.text = count.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterTextView = findViewById(R.id.counterTextView)
        incrementButton = findViewById(R.id.incrementButton)

        incrementButton.setOnClickListener {
            val serviceIntent = Intent(this, CounterService::class.java)
            startService(serviceIntent.putExtra("new_value", counterTextView.text.toString().toInt() + 1))
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("com.example.UPDATE_COUNTER")
        registerReceiver(counterReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(counterReceiver)
    }
}

class CounterService : Service() {
    private var counter = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getIntExtra("new_value", 0)?.let {
            counter = it
            val broadcastIntent = Intent("com.example.UPDATE_COUNTER")
            broadcastIntent.putExtra("count_value", counter)
            sendBroadcast(broadcastIntent)
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

class CounterBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val newCount = intent?.getIntExtra("count_value", 0)
    }
}
