package com.example.mvvmshop.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmshop.model.CategoryModel
import com.example.mvvmshop.repository.ShopRepository

class MainViewModel: ViewModel() {
    var shopRepository = ShopRepository()

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() {return _progress}

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() {return _error}

    private var _categoryList = MutableLiveData<List<CategoryModel>>()
    val categoryList: LiveData<List<CategoryModel>> get() {return _categoryList}

    fun getCatigories(){
        shopRepository.gerCategories(_progress, _error, _categoryList)
    }
}