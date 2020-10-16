package com.shubham.gitissues.model.GitIssues

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.GitIssues.GitIssueRepo
import com.shubham.gitissues.model.response.GitIssueResponse

class GitIssueRepoImpl : GitIssueRepo {
    private var allGitIssues = MutableLiveData<PagedList<GitIssueResponse>>()

    override fun getAllGitIssues(repoName : String) : MutableLiveData<PagedList<GitIssueResponse>>{
     //  var remoteApi = App.remoteApi
     ///  remoteApi.pullAlltheIssues(repoName) { tasks, error ->
     //      allGitIssues.value = tasks
     //  }

       return allGitIssues
    }
}