package com.example.gitapp.data.retrofit

import com.example.gitapp.data.retrofit.mappers.UserDtoMapper
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.domain.model.User
import io.reactivex.rxjava3.core.Single

class RetrofitUserRepository(
    private val apiService: GithubApiService,
    private val userDtoMapper: UserDtoMapper = UserDtoMapper()
) : IUserRepository {

    override fun getUser(userId: Int): Single<User> =
        apiService.getUser(userId).map {
            userDtoMapper.mapToDomain(it)
        }


    override fun getUsers(): Single<List<User>> = apiService.getUsers().map { usersDto ->
        usersDto.map {
            userDtoMapper.mapToDomain(it)
        }
    }
}