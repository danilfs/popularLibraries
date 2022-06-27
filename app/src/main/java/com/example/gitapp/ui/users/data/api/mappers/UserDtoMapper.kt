package com.example.gitapp.ui.users.data.api.mappers

import com.example.gitapp.ui.users.data.api.model.UserDto
import com.example.gitapp.ui.users.domain.model.User

class UserDtoMapper : DtoMapper<UserDto, User> {

    override fun mapToDomain(dtoEntity: UserDto) = User(
        dtoEntity.id,
        dtoEntity.login,
        dtoEntity.avatarUrl.orEmpty()
    )
}