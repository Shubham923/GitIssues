package com.shubham.gitissues.model.GitIssues

import android.os.Parcel
import android.os.Parcelable

class GitIssue(
    //val id : Int,
    val title : String?,
    val description: String?,
    public val avatar_url : String?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }



    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(description)
        dest?.writeString(avatar_url)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitIssue> {
        override fun createFromParcel(parcel: Parcel): GitIssue {
            return GitIssue(parcel)
        }

        override fun newArray(size: Int): Array<GitIssue?> {
            return arrayOfNulls(size)
        }
    }

}