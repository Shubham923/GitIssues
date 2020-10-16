package com.shubham.gitissues.viewmodel

import androidx.lifecycle.ViewModel
import com.shubham.gitissues.model.GitIssues.GitIssueRepo
import com.shubham.gitissues.model.GitIssues.GitIssueRepoImpl
import com.shubham.gitissues.model.GitRepos.GitRepo
import com.shubham.gitissues.model.GitRepos.GitRepoImpl

class AllGitReposViewModel(private val gitRepo: GitRepo = GitRepoImpl()) : ViewModel() {


    private val allGitReposLiveData = gitRepo.getAllGitRepos()
    fun getAllReposLiveData() = allGitReposLiveData
}