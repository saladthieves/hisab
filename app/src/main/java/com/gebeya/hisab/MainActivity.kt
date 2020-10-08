package com.gebeya.hisab

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

class MainActivity : BaseActivity(), CompoundButton.OnCheckedChangeListener,
    AdapterView.OnItemSelectedListener {

    private lateinit var fetiraCheckBox: CheckBox
    private val controller = Controller()
    val step = 15

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
                val final = progress * step
                totalPeopleLabel.text = "Total people: $final"
                controller.addPeople(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        }
        totalPeopleSeekBar.setOnSeekBarChangeListener(listener)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == null) return

        val id = buttonView.id

        val type = when(id) {
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

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}