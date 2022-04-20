package com.template

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.template.databinding.ActivityMainBinding


lateinit var mainBinding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        Handler().postDelayed({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)


        val animSplash = ObjectAnimator.ofFloat(mainBinding.splashImg, "scaleX", 0.5f)
        animSplash.duration = 1500

        animSplash.start()

        val animSplashSecond = ObjectAnimator.ofFloat(mainBinding.splashImg, "scaleY", 0.5f)
        animSplashSecond.duration = 1500

        animSplashSecond.start()

    }

}