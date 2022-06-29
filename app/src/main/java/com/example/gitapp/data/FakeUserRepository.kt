package com.example.gitapp.data

import android.os.Handler
import android.os.Looper
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.domain.model.User
import kotlin.random.Random

class FakeUserRepository : IUserRepository {

    private val fakeUsers = listOf(
        User(1, "mojombo"),
        User(2, "defunkt"),
        User(3, "pjhyett"),
        User(4, "wycats"),
        User(5, "ezmobius"),
        User(6, "ivey"),
        User(7, "evanphx"),
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