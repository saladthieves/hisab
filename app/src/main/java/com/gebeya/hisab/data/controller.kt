package com.gebeya.hisab.data

import com.gebeya.hisab.data.food.Food
import com.gebeya.hisab.data.food.FoodType
import com.gebeya.hisab.data.meal.Meal
import com.gebeya.hisab.data.meal.MealType
import com.gebeya.hisab.framework.util.logD

class Controller(
    val foods: MutableList<Food> = mutableListOf(),
    var meal: Meal = Meal(MealType.Breakfast),
    var peopleDiscount: Double = 0.0
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

    fun addMealType(type: MealType) {
        meal = Meal(type)
        log()
    }

    fun addPeople(people: Int) {
        peopleDiscount = when (people) {
            in 0..4 -> 0.0
            in 5..14 -> 0.3
            else -> 0.5
        }
        log()
    }

    fun getDiscounts(): Double {
        return meal.discount + peopleDiscount
    }

    fun calculate(): Double {
        val total = foods.sumByDouble { it.price }
        val discounts = getDiscounts() / 10.0
        val discountedAmount = total * discounts
        return total - discountedAmount
    }

    private fun log() {
        d("Foods: $foods")
        d("Meal: $meal")
        d("People discount: $peopleDiscount")
    }

    private fun d(message: String) {
        logD(this, message)
    }
}