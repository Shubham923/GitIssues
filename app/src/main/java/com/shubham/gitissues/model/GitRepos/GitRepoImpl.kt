package com.shubham.gitissues.model.GitRepos

import androidx.lifecycle.MutableLiveData
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse

class GitRepoImpl : GitRepo {
    private var allGitRepos = MutableLiveData<List<GitRepoResponse>>()




    init {
        //GitIssueStore.fetchGitIssues()
        var remoteApi = App.remoteApi
        remoteApi.pullAlltheRepos { tasks, error ->
            allGitRepos.value = tasks
        }

        //allGitIssues = GitIssueStore.getGitIssues()
    }
    override fun getAllGitRepos() = allGitRepos
}