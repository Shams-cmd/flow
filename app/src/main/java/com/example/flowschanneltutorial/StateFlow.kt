package com.example.flowschanneltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class StateFlow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow)

        // state flow was maintain the state value
        // last value of that state is state flow.
        // sstate flow was a hot nature.

        //Live data
        // In Live data all transformations are on Main Thread
        // In live data operators are limited
        // Live data depend on life cycle

        // Flow
        // you can run any thread ccording need :- main Thread,I.O etc
        // multiple operators
        // flow need couroutine scopes.3

        GlobalScope.launch(Dispatchers.Main) {

            val result=producer()
            delay(6000)
            result.collect{
                Log.d("stateflow","$it")
            }
        }

    }

    fun producer(): Flow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)


        }

        return mutableStateFlow
    }

    fun producer1(): Flow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)


        }

        return mutableStateFlow
    }
}