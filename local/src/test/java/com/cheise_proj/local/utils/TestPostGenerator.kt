package com.cheise_proj.local.utils

import com.cheise_proj.local.model.PostLocal

object TestPostGenerator {
    fun getPosts(): List<PostLocal> {
        return arrayListOf(
            PostLocal(
                id = "test uid",
                description = "test description",
                timestamp = "2020-03-31T15:45:28.627Z",
                user = TestUserGenerator.getUser(),
                proposition = "2 propositions",
                header = "test header",
                amount = "$ 0.00"
            ).apply {
                skills = arrayListOf("skill 1", "skill 2")
            }
        )

    }
}