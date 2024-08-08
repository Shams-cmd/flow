package com.example.flowschanneltutorial

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


fun main() {
    // Hot streams
// Hot streems produce data whether consumer is available or not.
// Hot streams resource wastage suppose we have hot stream they make a networ call and
// it was produce data but their was no consiumer to cosume  data in that case resource wastage.
// Manual close


    // if produce was fast there was a bottle neck was come in that case we make a buffer zone data buttzone also have a capacity
    // to store the data. same case in consumer also.
    // if produce was fast then coroutine suspend consumer
    // if consumer was fast then coroutine suspend oriducer


    // Stremes implements
    // producer consumer
    //Bottleneck
    //Asynchronous
    //cold

      GlobalScope.launch {
          val data = producer()
           data.collect{
               Log.d("shams1",it.toString())
           }
      }


}

fun producer() = flow<Int> {
      val list = listOf(1,2,3,4,5,6,7,8,9,10)

       list.forEach {
           delay(1000)
           emit(it)

       }
}


