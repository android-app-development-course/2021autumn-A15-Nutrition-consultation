package com.lnm011223.foods_secret

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.lnm011223.foods_secret.logic.model.Food

class FoodAdapter(val foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val foodName: TextView = view.findViewById(R.id.foodName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodList[position]
        // 使用默认图片
        holder.fruitImage.setImageResource(R.drawable.apple_pic)
        holder.foodName.text = food.name
    }

    override fun getItemCount() = foodList.size

}