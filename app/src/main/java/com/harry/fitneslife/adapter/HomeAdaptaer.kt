package com.harry.fitneslife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.R
import com.harry.fitneslife.funAndClass.HomeBox

class HomeAdaptaer(private val boxList: List<HomeBox>): RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var layout = LayoutInflater.from(parent.context)
        return HomeViewHolder(layout.inflate(R.layout.box_orange,parent,false))

    }

    override fun getItemCount(): Int {
        return boxList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = boxList[position]
        holder.render(item)

    }

}