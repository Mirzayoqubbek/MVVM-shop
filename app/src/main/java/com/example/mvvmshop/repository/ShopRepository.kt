package com.example.mvvmshop.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmshop.api.ApiManager
import com.example.mvvmshop.model.BaseResponse
import com.example.mvvmshop.model.CategoryModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.ScheduledFuture

class ShopRepository {
    var api = ApiManager.getApiInstance()
    var compositeDisposable = CompositeDisposable()

    fun gerCategories(
        progress: MutableLiveData<Boolean>,
        error: MutableLiveData<String>,
        success: MutableLiveData<List<CategoryModel>>
    ) {
        compositeDisposable.add(
            api.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>() {
                    override fun onNext(t: BaseResponse<List<CategoryModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                        //
                    }

                })
        )

    }
}