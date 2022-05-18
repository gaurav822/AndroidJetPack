package com.pranav.androidjetpackcourse.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pranav.androidjetpackcourse.R


class MyCustomListAdapter(private val listItems:List<String>,private val selection:String) : RecyclerView
                                                                                            .Adapter<DataViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_list,parent,false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = listItems[position]
        holder.tvText.text=currentItem

    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvText: TextView = itemView.findViewById(R.id.tv_text)
}