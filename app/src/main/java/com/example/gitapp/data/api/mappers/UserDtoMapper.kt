package com.example.gitapp.data.api.mappers

import com.example.gitapp.data.api.model.UserDto
import com.example.gitapp.domain.model.User

class UserDtoMapper : DtoMapper<UserDto, User> {

    override fun mapToDomain(dtoEntity: UserDto) = User(
        dtoEntity.id,
        dtoEntity.login,
    )
}