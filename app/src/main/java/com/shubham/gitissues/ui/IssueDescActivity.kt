package com.shubham.gitissues.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.gitissues.R
import com.shubham.gitissues.model.GitIssues.GitIssue
import kotlinx.android.synthetic.main.activity_issue_desc.*

class IssueDescActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_desc)

        setupIssues()
    }

    fun setupIssues() {
        val intent_ = intent.getParcelableExtra<GitIssue>("ISSUE_OBJECT")
        issue_title.text = intent_?.title
        issue_description.text = intent_?.description

    }
}