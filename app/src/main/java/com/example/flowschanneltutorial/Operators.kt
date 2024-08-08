package com.example.flowschanneltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class Operators : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)

        //flowas have two type operator
        //1.Termnal operator all termanal operator have suspend function.
        //2.Non Termanal operator

        GlobalScope.launch(Dispatchers.Main) {
            //Termanal operator
//           val result= producer().first()
//            val result2= producer().toList()
//            Log.d("first element", result2.toString())

            //Non Termanal operator
            producer().buffer(3).map {
            // Map into second operators
               Log.d("map",it.plus("data"))
            }
                .filter {
                    it.equals("one")
                }

                // collect operator is terminal and non terminal operator.
                .collect{
                  Log.d("Non Termanal operator",it.toString())
                }

            // Example

            GlobalScope.launch(Dispatchers.Main) {
                getNotes().map {
                    FormatedData(it.isActive,it.title.toUpperCase(),it.discription.toUpperCase())
                }
                    .filter {
                        it.isActive
                    }
                    .collect{
                        Log.d("Collect Notes",it.toString())
                    }
            }

        }


//        GlobalScope.launch(Dispatchers.Main) {
//
//            //onstrat will call on start
//            producer().onStart {
//                // you can manualy emit value
//                emit("data")
//                Log.d("Operators","onstart out")
//            }
//                    // oncompletion call on complete
//                .onCompletion {
//                    // you can manualy emit value
//                    emit("data1")
//                    Log.d("Operators","onCOmplete")
//                }
//                    // onEach will call on Each item
//                .onEach {
//                    Log.d("Operators","${it.toString()}")
//                }
//                // collect will consume data
//                .collect{
//                    Log.d("Operators","${it.toString()}")
//
//                }
//
//
//        }
    }

    fun producer() = flow<String> {
         val list = listOf<String>("one","two","three",
         "four","five","six","seven","eight","nine","ten")
          list.forEach {
              delay(1000)
               emit(it)
          }

    }
}
