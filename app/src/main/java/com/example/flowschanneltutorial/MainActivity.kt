package com.example.flowschanneltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

     val channel = Channel<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // kotlin has asyncronis streen support channels and flows

        // channels (send and receive data)
        // flows (emit and collect data )
        producer()
        consume()

    }

     private fun producer(){
       CoroutineScope(Dispatchers.Main).launch {
           channel.send("one")
           channel.send("Two")
       }
     }

    private fun consume(){
      CoroutineScope(Dispatchers.Main).launch{
         Log.d("Shams ",channel.receive())
          Log.d("Shams ",channel.receive())

      }
    }
}

