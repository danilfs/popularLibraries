package com.example.gitapp.data

import android.os.Handler
import android.os.Looper
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.domain.model.User
import io.reactivex.rxjava3.core.Single
import kotlin.random.Random

class FakeUserRepository : IUserRepository {

    private val fakeUsers = listOf(
        User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
        User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4"),
        User(4, "wycats", "https://avatars.githubusercontent.com/u/4?v=4"),
        User(5, "ezmobius", "https://avatars.githubusercontent.com/u/5?v=4"),
        User(6, "ivey", "https://avatars.githubusercontent.com/u/6?v=4"),
        User(7, "evanphx", "https://avatars.githubusercontent.com/u/7?v=4"),
    )

    override fun getUser(userId: Int): Single<User> =
        Single.just(fakeUsers.first { it.id == userId })

    override fun getUsers(): Single<List<User>> =
        Single.just(fakeUsers)

}