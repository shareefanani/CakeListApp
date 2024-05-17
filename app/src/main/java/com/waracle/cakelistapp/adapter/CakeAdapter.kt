package com.waracle.cakelistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waracle.cakelistapp.databinding.ItemCakeBinding
import com.waracle.cakelistapp.model.Cake

class CakeAdapter : RecyclerView.Adapter<CakeAdapter.CakeViewHolder>() {
    private var cakeList = ArrayList<Cake>()

    fun setCakeList(list : List<Cake>){
        this.cakeList = list as ArrayList<Cake>
        notifyDataSetChanged()
    }

    inner class CakeViewHolder(private val binding: ItemCakeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cake: Cake) {
            binding.titleTextView.text = cake.title
            binding.descTextView.text = cake.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        val binding = ItemCakeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        holder.bind(cakeList[position])
    }

    override fun getItemCount() = cakeList.size
}
