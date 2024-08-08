package com.example.flowschanneltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ProducerFlow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producer_flow)

     val job=   GlobalScope.launch {
            val data = producer()
            // if no consumer flows by default cancle
            data.collect{
                Log.d("shamsi",it.toString())
            }
        }
// multiple consumer only single flow
        GlobalScope.launch {
            val data = producer()
            // if no consumer flows by default cancle
            data.collect{
                Log.d("shamsi1",it.toString())
            }
        }

//        GlobalScope.launch {
//            delay(3500)
//            job.cancel()
//            Log.d("Cancle","Job was cancel")
//        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1,2,3,4,5,6,7,8,9,10)
         list.forEach {
             delay(1000)
             emit(it)
         }
    }
}