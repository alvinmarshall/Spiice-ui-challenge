package com.cheise_proj.spiice_ui_challenge.spiice.model

import android.os.Parcel
import android.os.Parcelable

data class Project(
    val id: String,
    val header: String,
    val owner: String,
    val status: Int,
    val postId: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(header)
        parcel.writeString(owner)
        parcel.writeInt(status)
        parcel.writeString(postId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Project> {
        override fun createFromParcel(parcel: Parcel): Project {
            return Project(parcel)
        }

        override fun newArray(size: Int): Array<Project?> {
            return arrayOfNulls(size)
        }
    }
}