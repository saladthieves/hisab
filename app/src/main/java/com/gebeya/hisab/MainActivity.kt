package com.gebeya.hisab

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import com.gebeya.hisab.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var fetiraCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetiraCheckBox = findViewById(R.id.fetiraCheck)
        fulMedamesCheck.setOnCheckedChangeListener(this)

        fetiraCheckBox.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        TODO("Not yet implemented")
    }
}