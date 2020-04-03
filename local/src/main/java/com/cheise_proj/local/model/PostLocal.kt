package com.cheise_proj.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.cheise_proj.local.mapper.post.PostLocalDataMapper

@Entity(tableName = "posts")
data class PostLocal(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val amount: String,
    val proposition: String,
    val header: String,
    val description: String,
    val timestamp: String,
    @Embedded
    val user: UserLocal
) {

    constructor():this("","","","","","",UserLocal())

    var skills: List<String> = arrayListOf()
    companion object {
        fun postMapper() = PostLocalDataMapper()
    }
}