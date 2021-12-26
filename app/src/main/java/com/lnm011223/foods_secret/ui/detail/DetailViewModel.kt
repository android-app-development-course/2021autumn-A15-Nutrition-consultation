package com.lnm011223.foods_secret.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnm011223.foods_secret.logic.model.Food
import com.lnm011223.foods_secret.logic.network.Repository

class DetailViewModel : ViewModel() {
    private val foodCategoryNameLiveData = MutableLiveData<String>()
    val foodListLiveData = Transformations.switchMap(foodCategoryNameLiveData) {
            foodCategoryName -> Repository.searchFoods(foodCategoryName)
    }

    fun getFoodList(foodCategoryName: String){
        foodCategoryNameLiveData.value = foodCategoryName
    }
}