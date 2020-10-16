package com.shubham.gitissues.model.networking

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse
import io.reactivex.disposables.CompositeDisposable

class GitRepoDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                               private val remoteApiService : RemoteApiService)
    : DataSource.Factory<Int, GitRepoResponse>() {
    val gitRepoDataSourceLiveData = MutableLiveData<GitRepoDataSource>()

    override fun create(): DataSource<Int, GitRepoResponse> {
        val gitRepoDataSource = GitRepoDataSource(remoteApiService, compositeDisposable)
        gitRepoDataSourceLiveData.postValue(gitRepoDataSource)
        return gitRepoDataSource
    }

}