package com.example.mvvmshop.api

import com.example.mvvmshop.model.BaseResponse
import com.example.mvvmshop.model.CategoryModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface Api {
    @GET("get_categories")
    fun getCategories(): Observable<BaseResponse<List<CategoryModel>>>
}