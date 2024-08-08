package com.example.flowschanneltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SharedFlow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_flow)
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect{
                Log.d("shams sharedFlow","Items1 -$it")
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
             val result = producer()
            delay(2500)
            result.collect{
                Log.d("shams sharedFlow","Items -$it")
            }
        }
    }

    fun producer() : Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>(1)
        GlobalScope.launch {
            val list = listOf(1,2,3,4,5)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }

        return mutableSharedFlow
    }
}