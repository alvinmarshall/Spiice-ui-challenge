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
                title = "Description"
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
                title = "Mobile Development"
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