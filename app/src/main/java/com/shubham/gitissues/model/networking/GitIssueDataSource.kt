package com.shubham.gitissues.model.networking

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.Response
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class GitIssueDataSource(private val apiService: RemoteApiService,
                         private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, GitIssueResponse>(){


    private var retrycompletable : Completable? = null
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GitIssueResponse>   //GitissueResponse was there
    ) {
        val repoName  = App.getRepositoryName()
        if (repoName != null) {
            Log.d("xyz-called", repoName)
        }
        compositeDisposable.add(
            apiService.pullAlltheIssues(repoName,App.getAPIToken(),App.getMediaType(),1,params.requestedLoadSize)
                .subscribe(
                    { response ->

                        Log.d("abc", response.toString())
                        callback.onResult(response,
                            null,
                            2
                        )
                    },
                    {
                        //setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitIssueResponse>) {

        val repoName = /*App.getRepositoryName()*/ App.getRepositoryName()
        compositeDisposable.add(
            apiService.pullAlltheIssues(repoName,App.getAPIToken(),App.getMediaType(),params.key,params.requestedLoadSize)
                .subscribe(
                    { response ->

                        Log.d("abc", response.toString())
                        callback.onResult(response,
                            params.key+1
                        )
                    },
                    {
                        //setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GitIssueResponse>
    ) {
    }


    fun retry() {
        if ( retrycompletable != null){
            compositeDisposable.add(retrycompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }

    }

}