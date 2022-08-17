package com.example.mvvmshop.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmshop.adapter.CategoryAdapter
import com.example.mvvmshop.databinding.ActivityMainBinding
import com.example.mvvmshop.model.CategoryModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.progress.observe(this, Observer {
            binding.progrrsbar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.categoryList.observe(this, Observer {
            setCategoryData(it)
        })

        loadData()
    }

    fun loadData(){
        viewModel.getCatigories()
    }

    fun setCategoryData(items: List<CategoryModel>){
        binding.recyler.layoutManager = GridLayoutManager(this, 2)
        binding.recyler.adapter = CategoryAdapter(items)
    }
}