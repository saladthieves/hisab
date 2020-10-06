package com.gebeya.hisab.data.meal

enum class MealType {
    Breakfast,
    Lunch,
    Dinner
}

private fun getDiscount(type: MealType) : Double {
    return when(type) {
        MealType.Breakfast -> 0.5
        MealType.Lunch -> 0.8
        MealType.Dinner -> 0.4
    }
}

data class Meal(val type: MealType) {
    val discount: Double
        get() {
            return getDiscount(type)
        }
}