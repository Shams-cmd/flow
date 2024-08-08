package com.example.flowschanneltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowContexExceptionHandeling : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_contex_exception_handeling)

        GlobalScope.launch(Dispatchers.Main) {
            producer().map {
                delay(1000)
                it * 2
                Log.d("Map","Map Thread - ${Thread.currentThread().name}")
            }.flowOn(Dispatchers.IO) // flowon context its helps to run the code on a perticular context.you can make multiple threads.
                .filter {
                    delay(500)
                    Log.d("Filter","Filter Thread - ${Thread.currentThread().name}")
                    it < 8
                }
                .flowOn(Dispatchers.Main)
                .collect{
                    Log.d("Collect","Collect Thread - ${Thread.currentThread().name}")
                }
        }
    }

    fun producer(): Flow<Int> {
        return flow {
            val list = listOf(1,2,3,4,5,6,7,8,9,10)
            list.forEach {
                delay(1000)
                Log.d("shamsCode","Emitter Thread -${Thread.currentThread().name} ")
                emit(it)
            }
        }
    }
}