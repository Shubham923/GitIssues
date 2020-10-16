package com.shubham.gitissues.model.GitIssues

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.shubham.gitissues.model.response.GitIssueResponse

interface GitIssueRepo {

    fun getAllGitIssues(repoName : String) : LiveData<PagedList<GitIssueResponse>>
}