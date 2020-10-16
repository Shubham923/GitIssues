package com.shubham.gitissues.model.networking

import android.util.Log
import androidx.paging.PagedList
import com.google.gson.Gson
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

const val BASE_URL = "https://api.github.com"
class RemoteApi(private val apiService: RemoteApiService) {


    private val gson = Gson()


 /*   fun pullAlltheIssues (repoName : String, onResponseReceived : (PagedList<GitIssueResponse>, Throwable?) -> Unit) {
        apiService.pullAlltheIssues(repoName,App.getAPIToken(), App.getMediaType()).enqueue(object :
            Callback<List<GitIssueResponse>> {
            override fun onFailure(call: Call<List<GitIssueResponse>>, t: Throwable) {
                Log.d("RemoteApi", t.toString())
                onResponseReceived(emptyList(), t)
            }

            override fun onResponse(
                call: Call<PagedList<GitIssueResponse>>,
                response: Response<PagedList<GitIssueResponse>> ) {
                val data = response.body()
                //val user = data?.get(0)?.user?.avatar_url
                if(data != null) {
                    onResponseReceived(data, null)
                }
                else if (data == null) {
                    onResponseReceived(emptyList(), NullPointerException("Oops, No Issues Found!"))
                }
                //Log.d("RemoteApi", data?.get(0)?.title.toString())
                //Log.d("RemoteApi", user.toString())
            }
        })
    }


  */


    fun pullAlltheRepos (onResponseReceived : (List<GitRepoResponse>, Throwable?) -> Unit) {
        apiService.pullAlltheRepos(App.getAPIToken(), App.getMediaType()).enqueue(object :
            Callback<List<GitRepoResponse>> {
            override fun onFailure(call: Call<List<GitRepoResponse>>, t: Throwable) {
                Log.d("RemoteApi", t.toString())

                onResponseReceived(emptyList(), t)

            }

            override fun onResponse(
                call: Call<List<GitRepoResponse>>,
                response: Response<List<GitRepoResponse>> ) {
                val data = response.body()
                val user = data?.get(0)?.user?.avatar_url

                if(data != null) {
                    onResponseReceived(data, null)
                }
                else if (data == null) {
                    onResponseReceived(emptyList(), NullPointerException("Oops, No Issues Found!"))
                }
                Log.d("RemoteApi", data?.get(0)?.title.toString())
                Log.d("RemoteApi", user.toString())

            }

        })
    }

}