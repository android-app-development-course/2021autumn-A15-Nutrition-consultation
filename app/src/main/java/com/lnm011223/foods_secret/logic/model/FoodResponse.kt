package com.lnm011223.foods_secret.logic.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
@Parcelize
data class Food(val name:String="",
                var isSelected:Boolean=true,
                var intakeAmount:Int = 100,
                @SerializedName("rl") var power:Float=0.0F,
                @SerializedName("dbz")val protein:Float=0.0F,
                @SerializedName("zf") var fat:Float=0.0F,
                @SerializedName("shhf") var carbohydrate:Float=0.0F,
                @SerializedName("ssxw") var diaryFiber:Float=0.0F):Parcelable {
    override fun equals(other: Any?): Boolean {
        if (other != null) {
            return if (other::class.java == Food::class.java) {
                val food = other as Food
                (food.name == name && food.protein == protein && food.fat == fat
                        && food.carbohydrate == carbohydrate && food.diaryFiber == diaryFiber)
            } else {
                false;
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + power.hashCode()
        result = 31 * result + protein.hashCode()
        result = 31 * result + fat.hashCode()
        result = 31 * result + carbohydrate.hashCode()
        result = 31 * result + diaryFiber.hashCode()
        return result
    }
}