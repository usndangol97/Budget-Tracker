package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class ChartsFragment : Fragment() {
    lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pieChart = view.findViewById(R.id.pieChart)
        setupChart()

    }

    fun setupChart(){
        val pieChartEntries = mutableListOf<PieEntry>()
        var i=0
        while(i<6){
            pieChartEntries.add(PieEntry(form_data.categoryArray[i].toFloat(),form_data.categoryTitles[i]))
            i++
        }
        pieChart.animateXY(1000,1000)

        val pieDataSet = PieDataSet(pieChartEntries,"Labels")
        pieDataSet.setColors(
            resources.getColor(R.color.red),
            resources.getColor(R.color.yellow),
            resources.getColor(R.color.blue),
            resources.getColor(R.color.green),
            resources.getColor(R.color.purple),
            resources.getColor(R.color.brown),

        )

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(11f)

        pieChart.data = pieData
        pieChart.setUsePercentValues(true)
        pieChart.isDrawHoleEnabled = false
        pieChart.description.isEnabled = false
        pieChart.setEntryLabelColor(R.color.black)
        pieChart.animateY(1400)
    }

}