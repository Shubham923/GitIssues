package com.shubham.gitissues.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.gitissues.R
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.GitIssues.GitIssue
import com.shubham.gitissues.model.State
import com.shubham.gitissues.viewmodel.AllGitIssuesViewModel
import kotlinx.android.synthetic.main.content_all_gitissues.*
import java.lang.Exception

class GitIssueActivity : AppCompatActivity() {

    private lateinit var viewModel : AllGitIssuesViewModel
    private lateinit var adapter : GitIssueAdapter



    companion object {
        fun switchIntent(context : Context, gitIssue : GitIssue) : Intent{
            val intent = Intent(context, IssueDescActivity::class.java)
            intent.putExtra("ISSUE_OBJECT", gitIssue)
            return intent
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_issue)

        Log.d("EXE","EXE")
       // GitIssueStore.fetchGitIssues(applicationContext)
        adapter = GitIssueAdapter{ viewModel.retry()}
        val repoName : String? = /*intent.getStringExtra("REPOSITORY_NAME")*/ App.getRepositoryName()
        viewModel = ViewModelProviders.of(this).get(AllGitIssuesViewModel :: class.java)
        gitIssuesRecyclerView.layoutManager = LinearLayoutManager(this)
        gitIssuesRecyclerView.adapter = adapter
        if (repoName != null) {
            viewModel.issueList.observe(this, Observer {
                    //adapter.updateGitIssues(gitIssues)
                   // Log.d("List", it[0]?.title.toString())
                    adapter.submitList(it)

            })
        }

        adapter.setState(State.DONE)


    }






}