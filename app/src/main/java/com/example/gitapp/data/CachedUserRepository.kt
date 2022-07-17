package com.example.gitapp.data

import android.util.Log
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.domain.model.User
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class CachedUserRepository(
    private val retrofitRepository: IUserRepository,
    private val roomRepository: IUserRepository
) : IUserRepository {

    override fun getUser(userId: Int): Flowable<User> {
        retrofitRepository.getUser(userId)
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext = { roomRepository.insert(listOf(it)) },
                onError = {}
            )

        return roomRepository.getUser(userId)
    }

    override fun getUsers(): Flowable<List<User>> {
        Log.d("@@@", "getUsers")
        retrofitRepository.getUsers()
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext = roomRepository::insert,
                onError = {}
            )

        return roomRepository.getUsers()
    }

}