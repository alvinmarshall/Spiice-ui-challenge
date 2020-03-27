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
                    id = "4",
                    name = "Kyle Wilson",
                    review = "Awesome job!",
                    ratingNumber = 4.2f,
                    timestamp = "05/03/2020"

                ),
                Review(
                    id = "3",
                    name = "Rosemary Copler",
                    review = "Alex is a very great designer, having a lot of positive energy with him!",
                    ratingNumber = 5f,
                    timestamp = "02/03/2020"
                ),
                Review(
                    id = "2",
                    name = "Soham Pena",
                    review = "Alex is a very great designer, having a lot of positive energy with him!",
                    ratingNumber = 5f,
                    timestamp = "17/02/2020"
                ),
                Review(
                    id = "1",
                    name = "Calvin Watson",
                    review = "I recommend Alex. Always impress by his work and his speed!",
                    ratingNumber = 5f,
                    timestamp = "19/01/2020"
                )
            )

        )
    }
    val getProfile: LiveData<Profile> = _setData
}