package com.shubham.gitissues.model.networking

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GitRepoDataSource(private val apiService: RemoteApiService,
                        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, GitRepoResponse>(){


    private var retrycompletable : Completable? = null
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GitRepoResponse>   //GitissueResponse was there
    ) {
        Log.d("xyz", "I am Called")
        compositeDisposable.add(
            apiService.pullAlltheRepos(App.getAPIToken(), App.getMediaType(),1,params.requestedLoadSize)
                .subscribe(
                    { response ->

                        Log.d("abcd", response.toString())
                        callback.onResult(response,
                            null,
                            2
                        )
                    },
                    {
                        Log.d("failing", it.localizedMessage)
                        //setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepoResponse>) {

        val repoName = "Spoon-Knife"
        compositeDisposable.add(
            apiService.pullAlltheRepos(App.getAPIToken(),
                App.getMediaType(),params.key,params.requestedLoadSize)
                .subscribe(
                    { response ->

                        Log.d("abcd", response.toString())
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
        callback: LoadCallback<Int, GitRepoResponse>
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