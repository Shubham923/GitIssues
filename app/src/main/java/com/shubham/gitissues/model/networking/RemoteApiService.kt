package com.shubham.gitissues.model.networking

import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse
import com.shubham.gitissues.model.response.Response
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import javax.security.auth.callback.Callback

interface RemoteApiService {

    @GET("/repos/octocat/{repository}/issues")
    fun pullAlltheIssues(
        @Path("repository")repoName: String?,
        @Header("Authorization") token : String,
        @Header("Accept") mediaType : String,
        @Query("page") page : Int,
    @Query("per_page")per_page : Int) : /*Call<List<GitIssueResponse>>*/ Single<List<GitIssueResponse>>

    @GET("/users/octocat/repos")
    fun pullAlltheRepos(@Header("Authorization") token : String,
                         @Header("Accept") mediaType : String,
                        @Query("page") page : Int,
                        @Query("per_page")per_page : Int): Single<List<GitRepoResponse>>
}