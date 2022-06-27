package com.example.gitapp.ui.users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gitapp.ui.users.domain.model.IUserRepository
import com.example.gitapp.ui.users.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class FakeUserRepository : IUserRepository {

    private val fakeUsers = listOf(
        User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
        User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4"),
        User(4, "wycats", "https://avatars.githubusercontent.com/u/4?v=4"),
    )

    private suspend fun userLoader(sinceId: Int, pageSize: Int): List<User> {
        delay(3000)
        return fakeUsers
    }

    override fun getUsers(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true)
    ) {
        UserPagingSource(::userLoader)
    }.flow

}