package com.shubham.gitissues.model.response

import com.shubham.gitissues.model.User
import com.squareup.moshi.Json

data class GitRepoResponse(
    @field:Json(name = "name") val title : String?,
    @field:Json(name = "owner")  val user : User?,
    @field:Json(name = "description")  val description : String?
) {
}