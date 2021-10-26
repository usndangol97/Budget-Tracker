package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.RecyclerViewData

class RecyclerViewAdapter(
    var listItems: List<RecyclerViewData>
):RecyclerView.Adapter<RecyclerViewAdapter.RviewViewHolder>() {

    companion object{
        var tabNo = 1
    }
    private var selectedItemPosition: Int = 0

    inner class RviewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val category = itemView.findViewById<TextView>(R.id.tvCategory)
        val title = itemView.findViewById<TextView>(R.id.displayTitle)
        val date = itemView.findViewById<TextView>(R.id.displayDate)
        val amount = itemView.findViewById<TextView>(R.id.displayAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return RviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RviewViewHolder, position: Int) {
        val item = listItems[position]
        when(tabNo){
            1->{
                selectedItemPosition = position
                if(selectedItemPosition == position){
                    holder.category.setBackgroundColor(Color.parseColor("#ff5252"))
                }
            }
            2->{
                selectedItemPosition = position
                if(selectedItemPosition == position){
                    holder.category.setBackgroundColor(Color.parseColor("#69f0ae"))
                }
            }
        }
        holder.apply {
            category.text = item.category
            title.text = item.title
            date.text = item.date
            amount.text = item.amount.toString()
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}