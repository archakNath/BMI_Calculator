package com.r.chuck.bmicalculator

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen2)
        Log.i("MYLOG", "SplashScreen started")

        //finish activity after 1.5 seconds ie. 1500ms
        object : CountDownTimer(1500, 100) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                Log.i("MYLOG", "SplashScreen ended")
                finish()
            }
        }.start()

        //change color of status bar
        setStatusBarColor(Color.parseColor("#ffffff"))
    }

    // status bar color update functions
    fun Activity.setStatusBarColor(color:Int){
        var flags = window?.decorView?.systemUiVisibility // get current flag
        if (flags != null) {
            if(isColorDark(color)){
                flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                window?.decorView?.systemUiVisibility = flags
            }else{
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window?.decorView?.systemUiVisibility = flags
            }
        }
        window?.statusBarColor = color
    }

    fun Activity.isColorDark(color:Int) : Boolean{
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return darkness >= 0.5
    }
}