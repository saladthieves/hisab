package com.gebeya.hisab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val intent = intent

        val selectedFoods = intent.getStringExtra(KEY_SELECTED_FOODS)
        val mealType = intent.getStringExtra(KEY_MEAL_TYPE)
        val discounts = intent.getDoubleExtra(KEY_DISCOUNT, 0.0);
        val servedBy = intent.getStringExtra(KEY_SERVED_BY)
        val total = intent.getDoubleExtra(KEY_TOTAL_AMOUNT, 0.0)

        summarySelectedFoodsLabel.text = selectedFoods
        summaryMealTypeLabel.text = mealType
        summaryTotalDiscountLabel.text = "${discounts.toInt() * 10}%"
        summaryServedByLabel.text = servedBy
        summaryTotalCostLabel.text = "$total ETB"

    }
}