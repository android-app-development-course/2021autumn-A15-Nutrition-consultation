package com.lnm011223.foods_secret.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnm011223.foods_secret.logic.network.Repository

class FoodViewModel:ViewModel()  {
    private val foodNameLiveData = MutableLiveData<String>()
    val foodLiveData = Transformations.switchMap(foodNameLiveData) {
        foodName -> Repository.getFoodInfo(foodName)
    }

    fun getFoodInfo(foodName: String){
        foodNameLiveData.value = foodName
    }
}