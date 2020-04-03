package com.cheise_proj.spiice_ui_challenge.spiice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cheise_proj.spiice_ui_challenge.spiice.model.Post
import com.cheise_proj.spiice_ui_challenge.spiice.model.User

class SearchViewModel : ViewModel() {

    private val _setData = MutableLiveData<List<Post>>().apply {
        value = arrayListOf(
            Post(
                id = "1",
                timestamp = "Posted 3 days ago",
                user = User(
                    id = "2",
                    email = "",
                    name = "Arlene Mckinney",
                    avatarUrl = "https://randomuser.me/api/portraits/med/women/75.jpg"
                ),
                content = "We are a young startup from Paris looking for a designer who can help us design a tech oriented application. We are open to proposals. You can saw our project here: \n" +
                        "http://www.zotware.com. We are working with Figma and Photoshop.",
                amount = "$ 2400",
                header = "Create an application",
                proposition = 16,
                skills = arrayListOf("UX/UI", "DESIGN", "FIGMA", "PHOTOSHOP"),
                title = "Description",
                elapseTime = "08/05/2020"
            ),

            Post(
                id = "2",
                timestamp = "Posted 10 days ago",
                user = User(
                    id = "2",
                    email = "",
                    name = "Wade Mccoy",
                    avatarUrl = "https://randomuser.me/api/portraits/med/men/52.jpg"
                ),
                content = "Create Spiice application now in React-Native from the shared link design, all asset are provided.",
                amount = "$ 6500",
                header = "React Native",
                proposition = 5,
                skills = arrayListOf("REDUX", "FIREBASE", "JAVASCRIPT"),
                title = "Mobile Development",
                elapseTime = "02/04/2020"
            ),
            Post(
                id = "3",
                timestamp = "Posted 8 days ago",
                user = User(
                    id = "3",
                    email = "",
                    name = "Francisco Fisher",
                    avatarUrl = "https://randomuser.me/api/portraits/med/men/85.jpg"
                ),
                content = "I need a designer for my new website The project is just at the beginning and I need wireframes before I start coding the website. I only want wireframes and I don’t want prototype or UI design.",
                amount = "$ 600",
                header = "Wireframes",
                proposition = 5,
                skills = arrayListOf("WIREFRAME"),
                title = "Description",
                elapseTime = "28/03/2020"
            )


        )

    }

    val getPost: LiveData<List<Post>> = _setData

    fun getPostById(identifier: String): LiveData<Post> {
        val data = MutableLiveData<Post>()
        _setData.value?.map { post ->
            if (identifier == post.id) {
                data.value = post
            }
        }
        return data
    }
}