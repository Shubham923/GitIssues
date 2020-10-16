package com.shubham.gitissues.networking

import com.shubham.gitissues.model.networking.RemoteApi
import com.shubham.gitissues.model.networking.RemoteApiService
import com.shubham.gitissues.model.networking.buildApiService
import com.shubham.gitissues.model.response.GitRepoResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class RemoteApiTest {


    @Mock
    val remoteApiService = buildApiService()
    val remoteApi = RemoteApi(remoteApiService)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun PullIssuesCalled() {
       // verify(remoteApi,times(1)).pullAlltheRepos(ArgumentMatchers.notNull())
        remoteApiService.pullAlltheIssues(anyString(),anyString(), anyString())
        verify(remoteApiService, times(1)).pullAlltheIssues(anyString(), anyString(),anyString())
    }

    @Test
    fun PullReposCalled() {
        // verify(remoteApi,times(1)).pullAlltheRepos(ArgumentMatchers.notNull())
        remoteApiService.pullAlltheRepos(anyString(),anyString())
        verify(remoteApiService, times(1)).pullAlltheRepos(anyString(), anyString())
    }

    @Test
    fun verifyRepos() {

        var titleToVerify = "git-consortium"
        var list : List<GitRepoResponse> = emptyList()
        remoteApi.pullAlltheRepos() {
            tasks, error ->
            list = tasks
            assertEquals(titleToVerify, list[1].title)
        }



    }


}