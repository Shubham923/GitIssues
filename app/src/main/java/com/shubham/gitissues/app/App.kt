package com.shubham.gitissues.app

import android.app.Application
import android.util.Log
import com.shubham.gitissues.model.GitIssues.GitIssue
import com.shubham.gitissues.model.networking.RemoteApi
import com.shubham.gitissues.model.networking.buildApiService

private val API_TOKEN = "ee843853e5265d6081210dcb8f04efe5f05b4906"
private val MEDIA_TYPE = "application/vnd.github.v3+json"
class App : Application(){


    companion object {
        private lateinit var instance: App
        fun getAPIToken() = API_TOKEN

        fun getMediaType() = MEDIA_TYPE

        var repoName : String? = null
        var userName : String? = null
        var gitIssueDescription : GitIssue? = null


        fun getRepositoryName() :String? {
            return repoName
        }

        fun setRepositoryName( str : String?) {

            repoName = str
        }



        val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}