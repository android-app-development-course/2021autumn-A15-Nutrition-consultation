package com.lnm011223.foods_secret.logic.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

/**
 * 网络数据源访问入口
 */
object FoodSecretNetwork {
    // 创建FoodService的动态代理对象
    private val foodService = ServiceCreator.create<FoodService>()

    // 声明为挂起函数，调用FoodService的方法
    suspend fun searchFoods(foodCategoryName:String, page:Int) = foodService.searchFoods(foodCategoryName, page).await()

    suspend fun getFoodInfo(foodName:String) = foodService.getSearchInfo(foodName).await()

    private suspend fun <T> Call<T>.await():T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}