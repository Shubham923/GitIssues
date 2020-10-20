package com.shubham.gitissues.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.shubham.gitissues.R
import com.shubham.gitissues.app.App
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        search_button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                App.userName = searchByName.text.toString()

                val intent = Intent(this@SearchActivity, GitRepoActivity::class.java)
                startActivity(intent)

            }

        } )


    }
}