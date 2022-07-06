package com.example.gitapp.data.room.mappers

import com.example.gitapp.domain.model.User

class UserEntityMapper : EntityMapper<UserEntity, User> {
    override fun mapToDomain(entity: UserEntity) = User(
        entity.id,
        entity.login,
        entity.avatarUrl
    )

    override fun mapToEntity(domain: User) = UserEntity(
        domain.id,
        domain.login,
        domain.avatarUrl
    )
}