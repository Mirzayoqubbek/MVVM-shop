package com.example.mvvmshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmshop.databinding.ActivityMainBinding
import com.example.mvvmshop.databinding.CategoryItemLayoutBinding
import com.example.mvvmshop.model.CategoryModel

class CategoryAdapter (var items: List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ItemHolder>(){
    inner class ItemHolder(var binding: CategoryItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item = items[position]
        holder.binding.tvtitle.text = item.title
        Glide.with(holder.itemView.context).load("http://osonsavdo.herokuapp.com/images/" + item.icon).into(holder.binding.imgcategory)
    }

    override fun getItemCount(): Int = items.count()
}