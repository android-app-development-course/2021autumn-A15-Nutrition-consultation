package com.lnm011223.foods_secret

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.contentValuesOf
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
        holder.checkBox.text = food.name
        holder.checkBox.isChecked = food.isSelected
//        holder.checkBox.isChecked = food.isSelected
        holder.checkBox.setOnClickListener {
            food.isSelected = holder.checkBox.isChecked
//            Toast.makeText(it.context,food.isSelected.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = foodselectList.size

}