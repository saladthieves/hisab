package com.gebeya.hisab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.SeekBar
import com.gebeya.hisab.data.Controller
import com.gebeya.hisab.data.food.FoodType
import com.gebeya.hisab.data.meal.MealType
import com.gebeya.hisab.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_SELECTED_FOODS = "SELECTED-FOODS"
const val KEY_MEAL_TYPE = "MEAL-TYPE"
const val KEY_DISCOUNT = "DISCOUNT"
const val KEY_SERVED_BY = "SERVED-BY"
const val KEY_TOTAL_AMOUNT = "TOTAL-AMOUNT"

class MainActivity : BaseActivity(), CompoundButton.OnCheckedChangeListener,
    AdapterView.OnItemSelectedListener {

    private lateinit var fetiraCheckBox: CheckBox
    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetiraCheckBox = findViewById(R.id.fetiraCheck)

        fetiraCheckBox.setOnCheckedChangeListener(this)
        fulMedamesCheck.setOnCheckedChangeListener(this)
        chechebsaCheck.setOnCheckedChangeListener(this)
        tibsCheck.setOnCheckedChangeListener(this)

        mealTypeSpinner.onItemSelectedListener = this
        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                totalPeopleLabel.text = "Total people: $progress"
                controller.addPeople(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        }
        totalPeopleSeekBar.setOnSeekBarChangeListener(listener)

        calculateButton.setOnClickListener {
            val selectedFoods = getSelectedFoods()
            val mealType = getMealType()
            val discounts = controller.getDiscounts()
            val servedBy = waiterNameInput.text.toString()
            val total = controller.calculate()

            val intent = Intent(this, SummaryActivity::class.java)

            intent.putExtra(KEY_SELECTED_FOODS, selectedFoods)
            intent.putExtra(KEY_MEAL_TYPE, mealType)
            intent.putExtra(KEY_DISCOUNT, discounts)
            intent.putExtra(KEY_SERVED_BY, servedBy)
            intent.putExtra(KEY_TOTAL_AMOUNT, total)

            startActivity(intent)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == null) return

        val id = buttonView.id

        val type = when (id) {
            R.id.fetiraCheck -> FoodType.Fetira
            R.id.fulMedamesCheck -> FoodType.FulMedames
            R.id.chechebsaCheck -> FoodType.Chechebsa
            else -> FoodType.Tibs
        }

        if (isChecked) {
            controller.addFood(type)
        } else {
            controller.removeFood(type)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val type = MealType.values()[position]
        controller.addMealType(type)
    }

    private fun getMealType() : String {
        return when(controller.meal.type) {
            MealType.Breakfast -> "Breakfast"
            MealType.Lunch -> "Lunch"
            MealType.Dinner -> "Dinner"
        }
    }

    private fun getSelectedFoods(): String {
        return controller.foods.map {
            it.type
        }.map {
            getFoodName(it)
        }.joinToString(", ")
    }

    private fun getFoodName(type: FoodType): String {
        return when (type) {
            FoodType.Fetira -> "Fetira"
            FoodType.FulMedames -> "Ful Medames"
            FoodType.Chechebsa -> "Chechebsa"
            FoodType.Tibs -> "Tibs"
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}