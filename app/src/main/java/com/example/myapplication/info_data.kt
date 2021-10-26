package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.dataSource
import com.google.android.material.floatingactionbutton.FloatingActionButton

class info_data : Fragment() {
    val dataSet = dataSource().loadData()
    val adapter = RecyclerViewAdapter(dataSet)

    companion object{
        var income = 0.0
        var expense = 0.0
    }

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvExpense = view.findViewById<TextView>(R.id.tvExpense)
        tvExpense.text = "Expense : \n$ "+expense.toString()

        val tvIncome = view.findViewById<TextView>(R.id.tvIncome)
        tvIncome.text = "Income : \n$ "+income.toString()

        val tvTotal = view.findViewById<TextView>(R.id.tvTotal)
        tvTotal.text = "Total : \n$ "+grandTotal().toString()

        val rv= view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)

        val floatingBtn = view.findViewById<FloatingActionButton>(R.id.fabBtn)
        floatingBtn.setOnClickListener(){
            goToNextScreen(view)
        }
    }

    fun goToNextScreen(view: View){
        navController = Navigation.findNavController(view)
        navController!!.navigate(R.id.action_info_data_to_form_data2)
    }
    /*fun fIncome():Double{
        val income = 0.00
        return income
    }

    fun fExpense():Double{
        val expense = 0.00
        return expense
    }*/

    fun grandTotal():Double{
        val inc = income
        val exp = expense
        val total = inc-exp
        return total
    }

}

