package com.shubham.gitissues.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.shubham.gitissues.R
import com.shubham.gitissues.ui.fragments.SearchFragment


class SplashscreenActivity : AppCompatActivity() {
    val SPLASH_TIME_OUT : Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed(Runnable { // This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashscreenActivity, SearchActivity::class.java)
            startActivity(i)

           // var fr = supportFragmentManager.beginTransaction()
          //  fr.replace(R.id.fragment_search_id, SearchFragment())

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}