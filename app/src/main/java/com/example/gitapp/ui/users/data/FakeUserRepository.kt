package com.example.gitapp.ui.users.data

import android.os.Handler
import android.os.Looper
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gitapp.ui.users.domain.model.IUserRepository
import com.example.gitapp.ui.users.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class FakeUserRepository : IUserRepository {

    private val fakeUsers = listOf(
        User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
        User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4"),
        User(4, "wycats", "https://avatars.githubusercontent.com/u/4?v=4"),
    )

    override fun getUsers(onSuccess: (List<User>) -> Unit, onError: (error: Throwable) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (Random.nextBoolean()) {
                    onSuccess(fakeUsers)
                } else {
                    onError(IllegalStateException("Something went wrong!"))
                }
            }, DATA_LOADING_DELAY
        )
    }

    companion object {
        const val DATA_LOADING_DELAY = 2_000L
    }

}