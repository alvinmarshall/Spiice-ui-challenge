package com.cheise_proj.spiice_ui_challenge.spiice.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheise_proj.spiice_ui_challenge.spiice.model.Portfolio
import com.cheise_proj.spiice_ui_challenge.spiice.model.Profile
import com.cheise_proj.spiice_ui_challenge.spiice.model.Review

class ProfileViewModel : ViewModel() {

    private val _setData = MutableLiveData<Profile>().apply {
        value = Profile(
            id = "1",
            name = "Dustin Warren",
            avatarUrl = "https://randomuser.me/api/portraits/med/men/75.jpg",
            description = "My name is Dustin, I’m a young designer from Dublin. I practice for 4 years now, worked with small and big agencies.",
            jobTitle = "UX Designer",
            portfolio = arrayListOf(
                Portfolio(
                    id = "1",
                    screenShotUrl = "https://picsum.photos/200/300?random=1"
                ), Portfolio(
                    id = "2",
                    screenShotUrl = "https://picsum.photos/200/300?random=2"
                )
                , Portfolio(
                    id = "3",
                    screenShotUrl = "https://picsum.photos/200/300?random=3"
                ), Portfolio(
                    id = "4",
                    screenShotUrl = "https://picsum.photos/200/300?random=4"
                )
            ),
            reviews = arrayListOf(
                Review(
                    id = "1",
                    name = "Derrick Austin",
                    review = "nice one",
                    ratingNumber = 4f
                ),
                Review(
                    id = "2",
                    name = "Kyle Wilson",
                    review = "Awesome job!",
                    ratingNumber = 4.2f
                )
            )

        )
    }
    val getProfile: LiveData<Profile> = _setData
}