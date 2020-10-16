package com.shubham.gitissues.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.gitissues.R
import com.shubham.gitissues.viewmodel.AllGitReposViewModel
import kotlinx.android.synthetic.main.content_all_gitissues.*

class GitRepoActivity : AppCompatActivity() {
    private lateinit var viewModel : AllGitReposViewModel
    private val adapter = GitRepoAdapter(mutableListOf())

    companion object {
        fun switchIntent(context : Context, repoName : String?) : Intent {
            val intent = Intent(context, GitIssueActivity::class.java)   //here change is needed
            //val gitIssueFragment = GitIssueFragment()
            intent.putExtra("REPOSITORY_NAME", repoName)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_repo)
        viewModel = ViewModelProviders.of(this).get(AllGitReposViewModel :: class.java)
        gitIssuesRecyclerView.layoutManager = LinearLayoutManager(this)
        gitIssuesRecyclerView.adapter = adapter
        viewModel.getAllReposLiveData().observe(this, Observer { gitRepos ->
            gitRepos?.let {
                Log.d("GITREPOACTIVITY", it[1].title.toString())
                adapter.updateGitIssues(gitRepos)
            }
        })
    }


}