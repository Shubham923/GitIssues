package com.shubham.gitissues.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shubham.gitissues.model.GitIssues.GitIssue
import java.io.IOException

object GitIssueStore {

    private lateinit var gitIssues : MutableLiveData<List<GitIssue>>
    private lateinit var gitIssuesList : List<GitIssue>

    fun fetchGitIssues(context: Context) {
        val gson = Gson()
        val json = loadFromFile("sample_issues.json", context)
        val listType = object : TypeToken<List<GitIssue>>(){}.type    //here i may need to change data type

        gitIssuesList = gson.fromJson(json, listType)
        //this.gitIssues.value = this.gitIssuesList
        populateList()

    }

    fun loadFromFile(filename : String, context: Context ) : String? {


        var json : String ? = null
        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)

        } catch (ex : IOException) {
            Log.e("CreatureStore", "Error Occurred", ex)
        }


        return json
    }

    fun getGitIssues() = gitIssues

    fun populateList() {
        gitIssues.value = listOf()
        for (i in gitIssuesList)
        {
            gitIssues.value = gitIssues.value?.plus(i)
        }
    }
}
