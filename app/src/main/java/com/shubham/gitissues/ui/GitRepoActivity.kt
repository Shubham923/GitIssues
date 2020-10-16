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
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.State
import com.shubham.gitissues.viewmodel.AllGitIssuesViewModel
import com.shubham.gitissues.viewmodel.AllGitReposViewModel
import kotlinx.android.synthetic.main.content_all_gitissues.*

class GitRepoActivity : AppCompatActivity() {
    private lateinit var viewModel: AllGitReposViewModel
    private lateinit var adapter : GitRepoAdapter

    companion object {
        fun switchIntent(context: Context, repoName: String?): Intent {
            val intent = Intent(context, GitIssueActivity::class.java)   //here change is needed
            //val gitIssueFragment = GitIssueFragment()
            App.setRepositoryName(repoName)
          //  Log.d("Checking RepoName", App.getRepositoryName().toString())
            intent.putExtra("REPOSITORY_NAME", repoName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_repo)
        adapter = GitRepoAdapter { viewModel.retry() }
        //val repoName: String? = intent.getStringExtra("REPOSITORY_NAME")
        viewModel = ViewModelProviders.of(this).get(AllGitReposViewModel::class.java)
        gitIssuesRecyclerView.layoutManager = LinearLayoutManager(this)
        gitIssuesRecyclerView.adapter = adapter
        viewModel.repoList.observe(this, Observer {
                //adapter.updateGitIssues(gitIssues)
                // Log.d("List", it[0]?.title.toString())
                adapter.submitList(it)
        })

        //adapter.setState(State.DONE)

    }
}