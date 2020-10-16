package com.shubham.gitissues.model.GitRepos

import androidx.lifecycle.LiveData
import com.shubham.gitissues.model.response.GitRepoResponse

interface GitRepo {

    fun getAllGitRepos() : LiveData<List<GitRepoResponse>>

}