package com.shubham.gitissues.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.GitIssues.GitIssueRepo
import com.shubham.gitissues.model.GitIssues.GitIssueRepoImpl
import com.shubham.gitissues.model.GitRepos.GitRepo
import com.shubham.gitissues.model.GitRepos.GitRepoImpl
import com.shubham.gitissues.model.networking.GitIssueDataSourceFactory
import com.shubham.gitissues.model.networking.GitRepoDataSourceFactory
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse
import io.reactivex.disposables.CompositeDisposable

class AllGitReposViewModel(private val gitRepo: GitRepo = GitRepoImpl()) : ViewModel() {


    var remoteApi = App.apiService
    var repoList: LiveData<PagedList<GitRepoResponse>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var gitRepoDataSourceFactory: GitRepoDataSourceFactory

    init {
        gitRepoDataSourceFactory = GitRepoDataSourceFactory( compositeDisposable, remoteApi)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        repoList = LivePagedListBuilder(gitRepoDataSourceFactory, config).build()

        // Log.d("IMP", issueList?.value?.get(0)?.title.toString())
    }

    fun retry() {
        gitRepoDataSourceFactory.gitRepoDataSourceLiveData
            .value?.retry()
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}