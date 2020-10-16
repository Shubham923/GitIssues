package com.shubham.gitissues.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.GitIssues.GitIssueRepo
import com.shubham.gitissues.model.GitIssues.GitIssueRepoImpl
import com.shubham.gitissues.model.networking.GitIssueDataSource
import com.shubham.gitissues.model.networking.GitIssueDataSourceFactory
import com.shubham.gitissues.model.networking.RemoteApiService
import com.shubham.gitissues.model.response.GitIssueResponse
import io.reactivex.disposables.CompositeDisposable

class AllGitIssuesViewModel(private val gitIssueRepo: GitIssueRepo = GitIssueRepoImpl()) : ViewModel(){
    var remoteApi = App.apiService
    var issueList: LiveData<PagedList<GitIssueResponse>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var gitIssueDataSourceFactory: GitIssueDataSourceFactory

    init {
        gitIssueDataSourceFactory = GitIssueDataSourceFactory( compositeDisposable, remoteApi)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        issueList = LivePagedListBuilder(gitIssueDataSourceFactory, config).build()

       // Log.d("IMP", issueList?.value?.get(0)?.title.toString())
    }

    fun getAllIssuesLiveData(repoName : String) : LiveData<PagedList<GitIssueResponse>>{

        return gitIssueRepo.getAllGitIssues(repoName)

    }


    fun retry() {
        gitIssueDataSourceFactory.gitIssuesDataSourceLiveData
            .value?.retry()
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}