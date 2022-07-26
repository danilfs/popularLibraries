package com.example.gitapp


import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.io.IOException
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

    override fun getUser(userId: Int): Flowable<User> =
        Single.just(fakeUsers.first { it.id == userId }).toFlowable()

    override fun getUsers(): Flowable<List<User>> =
        when (Random.nextBoolean()) {
            true -> Single.just(fakeUsers)
            false -> Single.error<List<User>>(IOException())
        }.toFlowable()
    
}