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
data class FoodResponse(val code:String, @SerializedName("newslist")val FoodInfoList:ArrayList<Food>)

/**
 * 食物信息(部分)
 * @param power 热量
 * @param protein 蛋白质
 * @param carbohydrate 碳水化合物
 * @param fat 脂肪
 * @param diaryFiber 膳食纤维
 */
data class Food(val name:String="",
                @SerializedName("rl")val power:Float=0.0F,
                @SerializedName("dbz")val protein:Float=0.0F,
                @SerializedName("zf")val fat:Float=0.0F,
                @SerializedName("shhf")val carbohydrate:Float=0.0F,
                @SerializedName("ssxw")val diaryFiber:Float=0.0F) {
}