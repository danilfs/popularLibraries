package com.example.gitapp

import com.chernorotov.gitapp.data.room.mappers.EntityMapper
import com.chernorotov.gitapp.data.room.model.UserEntity


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