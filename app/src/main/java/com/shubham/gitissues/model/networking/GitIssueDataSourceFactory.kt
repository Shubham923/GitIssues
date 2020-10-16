package com.shubham.gitissues.model.networking

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.shubham.gitissues.model.response.GitIssueResponse
import io.reactivex.disposables.CompositeDisposable

class GitIssueDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                private val remoteApiService : RemoteApiService)
    : DataSource.Factory<Int, GitIssueResponse>() {
    val gitIssuesDataSourceLiveData = MutableLiveData<GitIssueDataSource>()

    override fun create(): DataSource<Int, GitIssueResponse> {
        val gitIssueDataSource = GitIssueDataSource(remoteApiService, compositeDisposable)
        gitIssuesDataSourceLiveData.postValue(gitIssueDataSource)
        return gitIssueDataSource
    }

}