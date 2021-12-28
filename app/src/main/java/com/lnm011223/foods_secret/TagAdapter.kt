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

class TagAdapter (val tagNameList : List<Tag>) : RecyclerView.Adapter<TagAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagimageview : ImageView = view.findViewById(R.id.tagimageview)
        val tagnameview : TextView = view.findViewById(R.id.tagnameview)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }
    override fun onBindViewHolder(holder: TagAdapter.ViewHolder, position: Int) {
        val tag = tagNameList[position]
        holder.tagimageview.setImageResource(tag.tagimageId)
        holder.tagnameview.text = tag.tagname
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("tagname",tag.tagname)
            holder.itemView.findNavController().navigate(R.id.nav_detail, bundle)
        }
    }
    override fun getItemCount() = tagNameList.size
}