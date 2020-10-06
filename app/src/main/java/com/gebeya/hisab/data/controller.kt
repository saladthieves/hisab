package com.gebeya.hisab.data

import com.gebeya.hisab.data.food.Food
import com.gebeya.hisab.data.food.FoodType
import com.gebeya.hisab.framework.util.logD

class Controller(
    val foods: MutableList<Food> = mutableListOf()
) {
    fun addFood(type: FoodType) {
        val food = Food(type)
        if (food !in foods) {
            foods += food
        }
        log()
    }

    fun removeFood(type: FoodType) {
        val food = Food(type)
        if (food in foods) {
            foods -= food
        }
        log()
    }

    private fun log() {
        d("Foods: $foods")
    }

    private fun d(message: String) {
        logD(this, message)
    }
}