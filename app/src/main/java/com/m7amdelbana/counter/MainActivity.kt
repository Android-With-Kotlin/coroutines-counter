package com.m7amdelbana.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        button.setOnClickListener {
            CoroutineScope(IO).launch {
                plus(textView.text.toString().toInt() + 1)
            }
        }
    }

    private fun initUI() {
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
    }

    private suspend fun plus(value: Int) {
        delay(2000)
        setValue(value.toString())
    }

    private suspend fun setValue(value: String) {
        withContext(Main) {
            textView.text = value
        }
    }
}
