package com.cheise_proj.presentation.utils

import com.cheise_proj.presentation.model.Post

object TestPostGenerator {
    fun getPosts(): List<Post> {
        return arrayListOf(
            Post(
                id = "test uid",
                description = "test description",
                timestamp = "2020-03-31T15:45:28.627Z",
                user = TestUserGenerator.getUser(),
                skills = arrayListOf("skill 1", "skill 2"),
                proposition = "2 propositions",
                header = "test header",
                amount = "$ 0.00"
            )
        )

    }
}