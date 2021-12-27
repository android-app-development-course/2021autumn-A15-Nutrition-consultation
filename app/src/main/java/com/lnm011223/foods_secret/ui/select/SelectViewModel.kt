package com.lnm011223.foods_secret.ui.select

import androidx.lifecycle.ViewModel
import com.lnm011223.foods_secret.logic.model.Food

class SelectViewModel : ViewModel() {
    // 食物搭配集合
    var foodSet:HashSet<Food> = HashSet()
}