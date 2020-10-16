package com.shubham.gitissues.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.gitissues.R
import com.shubham.gitissues.app.App.Companion.remoteApi
import com.shubham.gitissues.model.GitIssueStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //GitIssueStore.fetchGitIssues(applicationContext)
       // val list_of_issues = GitIssueStore.getGitIssues()
       // test_file_read.setText(list_of_issues.get(0).description)
        /*
        var remoteApi = App.remoteApi

        remoteApi.pullAlltheIssues {
            issues, error ->


        }
        */






    }
}