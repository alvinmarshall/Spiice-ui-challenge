package utils

import com.cheise_proj.remote.model.posts.PostDto
import com.cheise_proj.remote.model.posts.PostRemote

object TestPostGenerator {
    fun getPostDto(): PostDto {
        return PostDto(
            post = getPosts(),
            status = 200
        )
    }

    private fun getPosts(): List<PostRemote> {
        return arrayListOf(
            PostRemote(
                id = "test uid",
                description = "test description",
                timestamp = "2020-03-31T15:45:28.627Z",
                user = TestUserGenerator.getUserDto().user,
                skills = arrayListOf("skill 1", "skill 2"),
                proposition = 2,
                header = "test header",
                amount = "$ 0.00"
            )
        )

    }
}