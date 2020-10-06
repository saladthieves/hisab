package com.gebeya.hisab

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import com.gebeya.hisab.data.Controller
import com.gebeya.hisab.data.food.FoodType
import com.gebeya.hisab.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), CompoundButton.OnCheckedChangeListener {

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
}