package com.chernorotov.gitapp.data.room.mappers

interface EntityMapper<E, D> {
    fun mapToDomain(entity: E): D
    fun mapToEntity(domain: D): E
}