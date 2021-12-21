package com.lnm011223.foods_secret.logic.model

import com.google.gson.annotations.SerializedName

/**
 * api说明地址：https://www.tianapi.com/apiview/121
 * 食品营养成分接口的种类列表和营养成分表:https://www.tianapi.com/article/110
 */
/**
 * 请求返回的参数
 * {
 *   "code": 200,
 *   "msg": "success",
 *   "newslist": [
 *     { }
 *  }
 */
data class FoodResponse(val code:String, val FoodInfoList:List<Food>)

/**
 * 食物信息(部分)
 */
data class Food(val name:String,
                @SerializedName("rl")val power:Int,
                @SerializedName("dbz")val protein:Int,
                @SerializedName("zf")val fat:Float) {
}