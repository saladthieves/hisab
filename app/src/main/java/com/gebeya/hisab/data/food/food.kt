package com.gebeya.hisab.data.food

enum class FoodType {
    Fetira,
    FulMedames,
    Chechebsa,
    Tibs,
}

fun getFoodPrice(type: FoodType) : Double {
    return when(type) {
        FoodType.Fetira -> 220.0
        FoodType.FulMedames -> 300.0
        FoodType.Chechebsa -> 280.0
        FoodType.Tibs -> 220.0
    }
}

data class Food(val type: FoodType) {
    val price: Double
        get() {
            return getFoodPrice(type)
        }
}