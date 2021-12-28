package com.lnm011223.foods_secret

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lnm011223.foods_secret.logic.model.Food

class FoodAdapter(val foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImage: ImageView = view.findViewById(R.id.foodImage)
        val foodName: TextView = view.findViewById(R.id.foodName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        val viewHolder = ViewHolder(view)


        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodList[position]
        // 使用默认图片
        holder.foodImage.setImageResource(R.drawable.select_image)
        holder.foodName.text = food.name
        holder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("foodName",food.name)
//            Toast.makeText(holder.itemView.context,"点击了${food.name}",Toast.LENGTH_SHORT).show()
            holder.itemView.findNavController().navigate(R.id.nav_search, bundle)
        }

    }

    override fun getItemCount() = foodList.size

}