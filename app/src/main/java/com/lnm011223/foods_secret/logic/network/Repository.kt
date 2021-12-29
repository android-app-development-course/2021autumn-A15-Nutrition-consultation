package com.lnm011223.foods_secret.logic.network

import android.util.Log
import androidx.lifecycle.liveData
import com.lnm011223.foods_secret.logic.model.Food
import kotlinx.coroutines.Dispatchers

/**
 * 仓库层
 */
object Repository {
    //
    fun searchFoods(foodCategoryName: String, page:Int) = liveData(Dispatchers.IO) {
        val result = try {
            val foodResponse = FoodSecretNetwork.searchFoods(foodCategoryName, page)
            if (foodResponse.code == "200") {
                val foods = foodResponse.FoodInfoList
                Result.success(foods)
            } else {
                Result.failure(RuntimeException("response status is ${foodResponse.code}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure<ArrayList<Food>>(e)
        }
        emit(result)
    }

    fun getFoodInfo(foodName: String) = liveData(Dispatchers.IO) {
        val result = try {
            val foodResponse = FoodSecretNetwork.getFoodInfo(foodName)
            if (foodResponse.code == "200") {
                if (foodResponse.FoodInfoList.isNotEmpty()) {
                    Result.success(foodResponse.FoodInfoList[0])
                } else {
                    Result.success(Food())
                }
            } else {
                Result.failure(RuntimeException("response status is ${foodResponse.code}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure<Food>(e)
        }
        emit(result)
    }
}