package com.lnm011223.foods_secret.logic.network

import com.lnm011223.foods_secret.MainActivity
import com.lnm011223.foods_secret.logic.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 食物查询接口
 * 接口参数见：https://www.tianapi.com/apiview/121
 */
interface FoodService {

    /**
     * 根据食物分类分页获取食物信息
     * @param foodCategoryName: 食物分类的名称
     * @param num ：返回数量（最大20）
     * @param page：分页
     */
    @GET("nutrient/index?key=${MainActivity.KEY}&mode=1")
    fun searchFoods(@Query("word")foodCategoryName:String, @Query("num")num:Int=8, @Query("page")page:Int=1) :Call<FoodResponse>

    /**
     * 根据食物名称获取食物信息
     * @param foodName：食物名称
     */
    @GET("nutrient/index?key=${MainActivity.KEY}&mode=0&num=1")
    fun getSearchInfo(@Query("word")foodName:String):Call<FoodResponse>
}