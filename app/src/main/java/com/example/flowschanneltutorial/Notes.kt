package com.example.flowschanneltutorial

import android.provider.ContactsContract.CommonDataKinds.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow


 fun getNotes(): Flow<Notes> {
    val list = listOf(
        Notes(1,true,"First","First Discription"),
                Notes(2,true,"Second","Second Discription"),
        Notes(3,true,"Third","Third Discription")

    )

    return list.asFlow() // asflow is a builder
}

data class Notes(val id : Int,val isActive : Boolean,val title : String,val discription : String)
data class FormatedData(val isActive: Boolean,val title: String,val discription: String)
