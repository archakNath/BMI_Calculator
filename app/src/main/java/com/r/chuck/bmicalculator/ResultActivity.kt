package com.r.chuck.bmicalculator

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.lang.Math.floor

class ResultActivity : AppCompatActivity() {
    private lateinit var imageViewSignal: ImageView
    private lateinit var textViewBMI1: TextView
    private lateinit var textViewBMI2: TextView
    private lateinit var textViewMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var color = ""
        var message = ""

        //initialisation
        imageViewSignal = findViewById(R.id.imageViewSignal)
        textViewBMI1 = findViewById(R.id.textViewBMI1)
        textViewBMI2 = findViewById(R.id.textViewBMI2)
        textViewMessage = findViewById(R.id.textViewMessage)

        setStatusBarColor(Color.parseColor("#ffffff"))

        val bmi = intent.getFloatExtra("bmi",0f)
        if(bmi == 0f){
            finish()
        } else {
            textViewBMI1.text = kotlin.math.floor(bmi.toDouble()).toInt().toString()
            textViewBMI2.text = bmi.toString().substring(bmi.toString().indexOf("."),bmi.toString().indexOf(".")+3)
        }
        when{
            bmi<18.50 ->{
                message = "Under\nWeight"
                color = R.color.yellow.toString()
            }
            bmi in 18.50..24.99 ->{
                message = "Normal"
                color = R.color.green.toString()
            }
            bmi in 25.00..29.99->{
                message = "Over\nWeight"
                color = R.color.yellow.toString()
            }
            bmi>29.99->{
                message = "Obese"
                color = R.color.red.toString()
            }
        }
//
        textViewMessage.setTextColor(ContextCompat.getColor(this, color.toInt()))
        textViewMessage.text = message
        if(message == "Normal"){
            imageViewSignal.setImageResource(R.drawable.ok)
        } else{
            imageViewSignal.setImageResource(R.drawable.warning)
        }
    }

    // status bar color update functions
    @Suppress("DEPRECATION")
    private fun Activity.setStatusBarColor(color: Int) {
        var flags = window?.decorView?.systemUiVisibility // get current flag
        if (flags != null) {
            if (isColorDark(color)) {
                flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                window?.decorView?.systemUiVisibility = flags
            } else {
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window?.decorView?.systemUiVisibility = flags
            }
        }
        window?.statusBarColor = color
    }

    private fun Activity.isColorDark(color: Int): Boolean {
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return darkness >= 0.5
    }
}