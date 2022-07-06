package com.example.gitapp.data.room

import com.example.gitapp.data.room.mappers.UserEntityMapper
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.domain.model.User
import io.reactivex.rxjava3.core.Single

class RoomUserRepository(
    private val userDao: UserDao,
    private val userMapper: UserEntityMapper = UserEntityMapper()
) : IUserRepository {
    override fun getUser(userId: Int): Single<User> =
        userDao.getUser(userId).map { userMapper.mapToDomain(it) }

    override fun getUsers(): Single<List<User>> = userDao.getUsers().map { usersEntity ->
        usersEntity.map { userMapper.mapToDomain(it) }
    }

    override fun insert(users: List<User>) {
        userDao.insert(users.map { userMapper.mapToEntity(it) })
    }
}