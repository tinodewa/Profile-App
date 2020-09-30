package com.hit.profileapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var mVisible: Boolean = false
    private lateinit var handler: Handler

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "app-intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        mVisible = true
        handler = Handler()
        handler.postDelayed({
            if (sharedPref.getBoolean(PREF_NAME, false)) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, AppIntroActivity::class.java)
                startActivity(intent)
                finish()
                val editor = sharedPref.edit()
                editor.putBoolean(PREF_NAME, true)
                editor.apply()
            }
        }, 2000)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_in_animation)
        iv_splash.animation = animation
    }
}