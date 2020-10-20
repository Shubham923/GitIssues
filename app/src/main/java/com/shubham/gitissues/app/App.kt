package com.shubham.gitissues.app

import android.app.Application
import android.util.Log
import com.shubham.gitissues.model.networking.RemoteApi
import com.shubham.gitissues.model.networking.buildApiService

private val API_TOKEN = "14b96a5d51509dc3a3b5dff8bc24ed5f8a67c64b"
private val MEDIA_TYPE = "application/vnd.github.v3+json"
class App : Application(){


    companion object {
        private lateinit var instance: App
        fun getAPIToken() = API_TOKEN

        fun getMediaType() = MEDIA_TYPE

        var repoName : String? = null
        var userName : String? = null


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