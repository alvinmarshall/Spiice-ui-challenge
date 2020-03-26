package com.cheise_proj.spiice_ui_challenge.spiice.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheise_proj.spiice_ui_challenge.spiice.model.Message
import com.cheise_proj.spiice_ui_challenge.spiice.model.User

class MessagesViewModel : ViewModel() {

    private val _setData = MutableLiveData<List<Message>>().apply {
        value = arrayListOf(
            Message(
                id = "1",
                content = "I have some questions about...",
                timeStamp = "",
                user = User(
                    id = "1",
                    avatarUrl = "https://randomuser.me/api/portraits/med/men/95.jpg",
                    name = "Alex Marshal",
                    email = ""
                )
            ),
            Message(
                id = "2",
                content = "http://www.warephase.com",
                timeStamp = "",
                user = User(
                    id = "2",
                    avatarUrl = "https://randomuser.me/api/portraits/med/women/85.jpg",
                    name = "Norma Wilson",
                    email = ""
                )
            ),
            Message(
                id = "3",
                content = "Hope it will work in the week...",
                timeStamp = "",
                user = User(
                    id = "3",
                    avatarUrl = "https://randomuser.me/api/portraits/med/men/45.jpg",
                    name = "Morris Murphy",
                    email = ""
                )
            ),
            Message(
                id = "4",
                content = "Thank you! It really shine with...",
                timeStamp = "",
                user = User(
                    id = "4",
                    avatarUrl = "https://randomuser.me/api/portraits/med/women/75.jpg",
                    name = "Kylie Lane",
                    email = ""
                )
            ),
            Message(
                id = "5",
                content = "I have some questions about",
                timeStamp = "Yes I know",
                user = User(
                    id = "5",
                    avatarUrl = "https://randomuser.me/api/portraits/med/men/60.jpg",
                    name = "Ted Steward",
                    email = ""
                )
            ),
            Message(
                id = "6",
                content = "It will be online in 2 days",
                timeStamp = "",
                user = User(
                    id = "6",
                    avatarUrl = "https://randomuser.me/api/portraits/med/men/52.jpg",
                    name = "Wade Mccoy",
                    email = ""
                )
            )

        )
    }
    val getMessages: LiveData<List<Message>> = _setData
}