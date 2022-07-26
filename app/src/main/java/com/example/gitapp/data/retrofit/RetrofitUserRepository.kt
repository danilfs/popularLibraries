package com.example.gitapp


import io.reactivex.rxjava3.core.Flowable

class RetrofitUserRepository(
    private val apiService: GithubApiService,
    private val userDtoMapper: UserDtoMapper = UserDtoMapper()
) : IUserRepository {

    override fun getUser(userId: Int): Flowable<User> =
        apiService.getUser(userId).map {
            userDtoMapper.mapToDomain(it)
        }.toFlowable()


    override fun getUsers(): Flowable<List<User>> = apiService.getUsers().map { usersDto ->
        usersDto.map {
            userDtoMapper.mapToDomain(it)
        }
    }.toFlowable()
}