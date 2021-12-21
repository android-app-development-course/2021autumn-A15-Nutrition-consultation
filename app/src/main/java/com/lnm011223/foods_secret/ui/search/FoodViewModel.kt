package com.lnm011223.foods_secret.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnm011223.foods_secret.logic.model.Food
import com.lnm011223.foods_secret.logic.network.Repository

class FoodViewModel:ViewModel()  {
    private val searchLiveData = MutableLiveData<String>()

    val foodList = ArrayList<Food>()
    

    val foodLiveData = Transformations.switchMap(searchLiveData) {
        foodCategoryName -> Repository.searchFoods(foodCategoryName)
    }

    fun searchFoodByCategoryName(foodCategoryName:String) {
        searchLiveData.value = foodCategoryName
    }

}