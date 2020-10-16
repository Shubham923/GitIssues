package com.shubham.gitissues.networking

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.shubham.gitissues.app.MainActivity
import com.shubham.gitissues.model.GitIssueStore
import com.shubham.gitissues.model.networking.NetworkStatusChecker
import com.shubham.gitissues.ui.GitIssueActivity
import com.shubham.gitissues.ui.GitRepoActivity
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NetworkStatusCheckerTest {


    @Mock
    lateinit var connectivityManager : ConnectivityManager

    @Mock
    var actContext = GitRepoActivity()
    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        connectivityManager.allNetworks
    }

    @Test
    fun checkInternetConnection() {

        var networkStatusChecker =NetworkStatusChecker( actContext.getSystemService(ConnectivityManager::class.java))

        val value = networkStatusChecker.hasInternetConnection()
        assertTrue(value)
    }

    @Test
    fun checkNoInternetConnection() {

        var networkStatusChecker =NetworkStatusChecker(actContext.getSystemService(ConnectivityManager::class.java))

        val value = networkStatusChecker.hasInternetConnection()
        assertFalse(value)
    }
}