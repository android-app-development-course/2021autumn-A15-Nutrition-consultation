package com.lnm011223.foods_secret

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.parseColor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lnm011223.foods_secret.logic.model.GetResult

class ResultAdapter (val resultList : List<GetResult>) : RecyclerView.Adapter<ResultAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val left_image : ImageView = view.findViewById(R.id.left_image)
        val result_titletext : TextView = view.findViewById(R.id.result_titletext)
        val result_bodytext : TextView = view.findViewById(R.id.result_bodytext)
        val result_card : CardView = view.findViewById(R.id.result_card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }
    @SuppressLint("SetTextI18n", "ResourceType")
    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        val getResult = resultList[position]
        holder.left_image.setBackgroundColor(parseColor(changecolor(getResult.nourishment_name)))
        holder.result_card.apply {

            //setBackgroundColor(changecolor1(getResult.nourishment_name))
            setCardBackgroundColor(changecolor1(getResult.nourishment_name))

        }
        holder.result_titletext.apply {
            text = getResult.nourishment_name + ": ${String.format("%.2f",getResult.nourishment_intake)}"
            setTextColor(parseColor(changecolor(getResult.nourishment_name)))
        }
        holder.result_bodytext.text = getResult.result_text
    }
    override fun getItemCount() = resultList.size
    private fun changecolor( name: String) : String = when {
        name == "热量" -> "#E57373"
        name == "脂肪" -> "#BA68C8"
        name == "蛋白质" -> "#7986CB"
        name == "碳水化合物" -> "#64B5F6"
        name == "膳食纤维" -> "#81C784"
        else -> "#FF8A65"
    }
    private fun changecolor1( name: String) : Int = when {
        name == "热量" -> parseColor("#FFEBEE")
        name == "脂肪" -> parseColor("#EDE7F6")
        name == "蛋白质" -> parseColor("#E8EAF6")
        name == "碳水化合物" -> parseColor("#E3F2FD")
        name == "膳食纤维" -> parseColor("#F1F8E9")
        else -> parseColor("#FF8A65")
    }
}