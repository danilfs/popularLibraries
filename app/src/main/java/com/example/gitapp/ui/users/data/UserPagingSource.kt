package com.example.gitapp.ui.users.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitapp.ui.users.domain.model.User

class UserPagingSource (private val userLoader: suspend (sinceId: Int, count: Int) -> List<User>) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> =
        try {
            val sinceId = params.key ?: 0
            val pageSize = params.loadSize
            val users = userLoader(sinceId, pageSize)
            LoadResult.Page(
                data = users,
                prevKey = null,
                nextKey = if (users.size < pageSize) null else users.last().id
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}