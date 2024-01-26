package com.harry.fitneslife.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.databinding.BoxOrangeBinding
import com.harry.fitneslife.funAndClass.HomeBox

class HomeViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    val binding = BoxOrangeBinding.bind(view)
    fun render(HomeBoxModel: HomeBox) {
        binding.textCarr.text = HomeBoxModel.title
        binding.IconCarr.setImageResource(HomeBoxModel.icon)

    }

}