package com.pranav.androidjetpackcourse.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pranav.androidjetpackcourse.databinding.ItemDishLayoutBinding
import com.pranav.androidjetpackcourse.model.entities.FavDish


class FavDishAdapter(private val fragment:Fragment) : RecyclerView.Adapter<FavDishAdapter.ViewHolder>() {

    private var dishes:List<FavDish> = listOf()

    class ViewHolder(view:ItemDishLayoutBinding): RecyclerView.ViewHolder(view.root) {
        val ivDishImage = view.ivDishImage
        val tvTitle = view.tvDishTitle
        val deleteIcon = view.deleteIcon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDishLayoutBinding = ItemDishLayoutBinding.inflate(
            LayoutInflater.from(fragment.context),parent,false
                                                                          )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eachDish = dishes[position]
        Glide.with(fragment)
            .load(eachDish.image)
            .into(holder.ivDishImage)
        holder.tvTitle.text= eachDish.title
        holder.deleteIcon.setOnClickListener {
            Toast.makeText(fragment.context,"Delete Icon Clicked",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    fun dishesList(list: List<FavDish>){
        dishes = list
        notifyDataSetChanged()
    }
}