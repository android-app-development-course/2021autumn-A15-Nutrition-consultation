package com.lnm011223.foods_secret

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lnm011223.foods_secret.logic.model.Food

class FoodSelectAdapter (val foodselectList: List<Food>) : RecyclerView.Adapter<FoodSelectAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox : CheckBox = view.findViewById(R.id.foodCheckBox)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodSelectAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.foodselect_item, parent, false)
        val viewHolder = ViewHolder(view)


        return viewHolder
    }
    override fun onBindViewHolder(holder: FoodSelectAdapter.ViewHolder, position: Int) {
        val food = foodselectList[position]
        // 使用默认图片
        holder.checkBox.text = food.name
        holder.itemView.setOnClickListener {
            if (holder.checkBox.isChecked){
                holder.checkBox.isChecked = false
                
            }else{
                holder.checkBox.isChecked

            }


        }

    }

    override fun getItemCount() = foodselectList.size

}