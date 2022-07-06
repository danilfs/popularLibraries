package com.example.gitapp.data.retrofit.mappers

import com.example.gitapp.data.api.model.UserDto
import com.example.gitapp.domain.model.User

class UserDtoMapper : DtoMapper<UserDto, User> {

    override fun mapToDomain(dtoEntity: UserDto) = User(
        dtoEntity.id,
        dtoEntity.login,
        dtoEntity.avatarUrl.orEmpty(),
    )
}