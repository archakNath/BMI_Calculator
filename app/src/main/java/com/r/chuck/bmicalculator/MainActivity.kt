package com.r.chuck.bmicalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //declarations
    private lateinit var splashIntent: Intent
    private lateinit var resultIntent: Intent
    private lateinit var editTextAge: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var buttonCalculate: ImageButton
    private lateinit var buttonAgeDecrease: ImageButton
    private lateinit var buttonAgeIncrease: ImageButton
    private lateinit var buttonWeightDecrease: ImageButton
    private lateinit var buttonWeightIncrease: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MYLOG", "Main Activity started")
        //initialisations
        resultIntent = Intent(this, ResultActivity::class.java)
        editTextAge = findViewById(R.id.editTextAge)
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextHeight = findViewById(R.id.editTextHeight)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonWeightIncrease = findViewById(R.id.imageButtonWeightIncrease)
        buttonWeightDecrease = findViewById(R.id.imageButtonWeightDecrease)
        buttonAgeDecrease = findViewById(R.id.imageButtonAgeDecrease)
        buttonAgeIncrease = findViewById(R.id.imageButtonAgeIncrease)

        var weight: Float
        var height: Float
        var bmi: Float

        //splash screen
        splashIntent = Intent(this, SplashScreen::class.java)
        startActivity(splashIntent)

        // change status bar color to #ffffff(white)
        setStatusBarColor(Color.parseColor("#ffffff"))

        //button on click listeners
        buttonCalculate.setOnClickListener {
            if (editTextAge.text.toString() == "") {
                editTextAge.error = "Enter your age!"
            } else if (editTextWeight.text.toString() == "") {
                editTextWeight.error = "Enter your weight!"
            } else if (editTextHeight.text.toString() == "") {
                editTextHeight.error = "Enter your height!"
            } else {
                weight = editTextWeight.text.toString().toFloat()
                height = editTextHeight.text.toString().toFloat() / 100
                bmi = weight / (height * height)
                resultIntent.putExtra("bmi", bmi)
                startActivity(resultIntent)
            }
        }

        //increment and decrement buttons
        buttonAgeDecrease.setOnClickListener {
            if (!TextUtils.isEmpty(editTextAge.text.toString()) && editTextAge.text.toString()
                    .toInt() > 0
            ) {
                editTextAge.setText((editTextAge.text.toString().toInt() - 1).toString())
            }
        }
        buttonAgeIncrease.setOnClickListener {
            if (TextUtils.isEmpty(editTextAge.text.toString())) {
                editTextAge.setText("0")
            }
            editTextAge.setText((editTextAge.text.toString().toInt() + 1).toString())
        }
        buttonWeightDecrease.setOnClickListener {
            if (!TextUtils.isEmpty(editTextWeight.text.toString()) && editTextWeight.text.toString()
                    .toInt() > 0
            ) {
                editTextWeight.setText((editTextWeight.text.toString().toInt() - 1).toString())
            }
        }
        buttonWeightIncrease.setOnClickListener {
            if (TextUtils.isEmpty(editTextWeight.text.toString())) {
                editTextWeight.setText("0")
            }
            editTextWeight.setText((editTextWeight.text.toString().toInt() + 1).toString())
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

