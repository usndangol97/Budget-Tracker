package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.model.RecyclerViewData
import com.example.myapplication.model.dataSource
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*


class form_data : Fragment() {
    /*category array of (food, trasport, apparels, education, accessories, social life)*/

    companion object{
        var categoryArray = arrayOf<Double>(0.0,0.0,0.0,0.0,0.0,0.0)
        var categoryIncomeArray = arrayOf<Double>(0.0,0.0,0.0)
        val categoryTitles = arrayOf<String>("Food", "Transport", "Apparels", "Education", "Accessories", "Social life")
        val categoryIncomeTitles = arrayOf<String>("Salary", "Allowance", "Bonus")
    }


    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textDisplay = view.findViewById<TextView>(R.id.displayCategory)
        val amountField = view.findViewById<EditText>(R.id.textFieldAmount)
        val titleField = view.findViewById<EditText>(R.id.textFieldTitle)
        val saveBtn = view.findViewById<Button>(R.id.saveBtn)

        var tabLayout :TabLayout? =null
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        var mark =1
        var tabMark = 1
        var incMark = 1

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        view.findViewById<GridLayout>(R.id.incomeBtnGrid).setVisibility(View.GONE)
                        view.findViewById<GridLayout>(R.id.expenseBtnGrid).setVisibility(View.VISIBLE)
                        tabMark = 1
                        textDisplay.text = getString(R.string.food)
                        mark =1
                    }
                    1->{
                        view.findViewById<GridLayout>(R.id.incomeBtnGrid).setVisibility(View.VISIBLE)
                        view.findViewById<GridLayout>(R.id.expenseBtnGrid).setVisibility(View.GONE)
                        tabMark = 2
                        textDisplay.text = getString(R.string.salary)
                        incMark = 1
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        val btnFood = view.findViewById<Button>(R.id.c1)
        btnFood.setOnClickListener() {
            textDisplay.text = getString(R.string.food)
            mark =1
        }
        val btnTransport = view.findViewById<Button>(R.id.c2)
        btnTransport.setOnClickListener() {
            textDisplay.text = getString(R.string.transp)
            mark =2
        }
        val btnApparels = view.findViewById<Button>(R.id.c3)
        btnApparels.setOnClickListener() {
            textDisplay.text = getString(R.string.clothes)
            mark =3
        }
        val btnEdu = view.findViewById<Button>(R.id.c4)
        btnEdu.setOnClickListener() {
            textDisplay.text = getString(R.string.edu)
            mark =4
        }
        val btnAcc = view.findViewById<Button>(R.id.c5)
        btnAcc.setOnClickListener() {
            textDisplay.text = getString(R.string.acc)
            mark =5
        }
        val btnSocial = view.findViewById<Button>(R.id.c6)
        btnSocial.setOnClickListener() {
            textDisplay.text = getString(R.string.social)
            mark =6
        }

        view.findViewById<Button>(R.id.inc1).setOnClickListener{
            textDisplay.text = getString(R.string.salary)
            incMark = 1
        }
        view.findViewById<Button>(R.id.inc2).setOnClickListener{
            textDisplay.text = getString(R.string.allowance)
            incMark = 2
        }
        view.findViewById<Button>(R.id.inc3).setOnClickListener{
            textDisplay.text = getString(R.string.bonus)
            incMark = 3
        }



        saveBtn.setOnClickListener(){
            when(tabMark){
                1->{
                    when(mark){
                        1->categoryArray[0] += amountField.text.toString().toDouble()
                        2->categoryArray[1] += amountField.text.toString().toDouble()
                        3->categoryArray[2] += amountField.text.toString().toDouble()
                        4->categoryArray[3] += amountField.text.toString().toDouble()
                        5->categoryArray[4] += amountField.text.toString().toDouble()
                        6->categoryArray[5] += amountField.text.toString().toDouble()
                    }
                    info_data.expense = expenseTotal()
                    RecyclerViewAdapter.tabNo = tabMark

                    val tempData = RecyclerViewData(categoryTitles[mark-1],
                        titleField.text.toString(),
                        getCurrentDate(),
                        amountField.text.toString().toDouble())
                    val tempList = dataSource.dataList
                    tempList.add(tempData)
                }
                2->{
                    when(mark){
                        1->categoryIncomeArray[0] += amountField.text.toString().toDouble()
                        2->categoryIncomeArray[1] += amountField.text.toString().toDouble()
                        3->categoryIncomeArray[2] += amountField.text.toString().toDouble()
                    }
                    info_data.income = incomeTotal()
                    RecyclerViewAdapter.tabNo = tabMark

                    val tempData = RecyclerViewData(categoryIncomeTitles[incMark-1],
                        titleField.text.toString(),
                        getCurrentDate(),
                        amountField.text.toString().toDouble())
                    val tempList = dataSource.dataList
                    tempList.add(tempData)
                }
            }


            toNextScreen(view)
        }
    }

    fun expenseTotal():Double{
        var total = 0.0
        for(i in 0..categoryArray.size - 1){
            total += categoryArray[i]
        }
        return total
    }

    fun incomeTotal():Double{
        var total = 0.0
        for(i in 0..categoryIncomeArray.size - 1){
            total += categoryIncomeArray[i]
        }
        return total
    }

    fun getCurrentDate():String{
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MMM d yyyy")
        val date = simpleDateFormat.format(calendar.time)
        return date
    }

    fun toNextScreen(view: View){
        navController = Navigation.findNavController(view)
        navController!!.navigate(R.id.action_form_data2_to_info_data)
    }

}