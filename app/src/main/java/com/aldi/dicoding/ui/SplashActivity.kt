package com.aldi.dicoding.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.aldi.dicoding.R
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val typeface = ResourcesCompat.getFont(this, R.font.blacklist)

        val appname:TextView = findViewById(R.id.appname)
        appname.typeface = typeface

        YoYo.with(Techniques.Bounce)
            .duration(7000)
            .playOn(findViewById<View>(R.id.logo))

        YoYo.with(Techniques.FadeInUp)
            .duration(5000)
            .playOn(findViewById<View>(R.id.appname))

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private val SPLASH_TIME_OUT = 3000
    }


}
