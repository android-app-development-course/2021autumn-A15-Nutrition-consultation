package com.lnm011223.foods_secret.logic.network

import androidx.lifecycle.liveData
import com.lnm011223.foods_secret.logic.model.Food
import kotlinx.coroutines.Dispatchers

/**
 * 仓库层
 */
object Repository {
    //
    fun searchFoods(foodCategoryName: String) = liveData(Dispatchers.IO) {
        val result = try {
            val foodResponse = FoodSecretNetwork.searchFoods(foodCategoryName)
            if (foodResponse.code == "200") {
                val foods = foodResponse.FoodInfoList
                Result.success(foods)
            } else {
                Result.failure(RuntimeException("response status is ${foodResponse.code}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Food>>(e)
        }
        emit(result)
    }

    fun getFoodInfo(foodName: String) = liveData(Dispatchers.IO) {
        val result = try {
            val foodResponse = FoodSecretNetwork.searchFoods(foodName)
            if (foodResponse.code == "200") {
                val food = foodResponse.FoodInfoList[0]
                Result.success(food)
            } else {
                Result.failure(RuntimeException("response status is ${foodResponse.code}"))
            }
        } catch (e: Exception) {
            Result.failure<Food>(e)
        }
        emit(result)
    }
}