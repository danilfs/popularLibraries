package com.example.gitapp.data.room.mappers

interface EntityMapper<E, D> {
    fun mapToDomain(entity: E): D
    fun mapToEntity(domain: D): E
}