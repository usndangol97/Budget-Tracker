package com.example.myapplication.model

import java.text.SimpleDateFormat
import java.util.*

class dataSource {
    companion object{
        var dataList = mutableListOf<RecyclerViewData>(
        )
        fun getCurrentDate():String{
            val calendar = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat("MMM d yyyy")
            val date = simpleDateFormat.format(calendar.time)
            return date
        }
    }

    fun loadData(): List<RecyclerViewData>{
        return dataList
    }

}