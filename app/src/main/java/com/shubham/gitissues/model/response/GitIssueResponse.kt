package com.shubham.gitissues.model.response

import com.shubham.gitissues.model.User
import com.squareup.moshi.Json

data class GitIssueResponse(
    @field:Json(name = "title") val title : String?,
    @field:Json(name = "user")  val user : User?,
    @field:Json(name = "body")  val description : String?


)




//was able to read all the titles. now further refactoring needed.