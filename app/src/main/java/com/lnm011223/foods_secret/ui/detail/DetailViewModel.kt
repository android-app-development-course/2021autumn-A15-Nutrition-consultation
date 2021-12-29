package com.lnm011223.foods_secret.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnm011223.foods_secret.logic.model.FoodRequest
import com.lnm011223.foods_secret.logic.network.Repository

class DetailViewModel : ViewModel() {
    private val requestParam = MutableLiveData<FoodRequest>()
    val foodListLiveData = Transformations.switchMap(requestParam) {
        param -> Repository.searchFoods(param.categoryName, param.page)
    }

    fun getFoodList(foodCategoryName: String, page:Int){
        requestParam.value =FoodRequest(foodCategoryName, page)
    }

}