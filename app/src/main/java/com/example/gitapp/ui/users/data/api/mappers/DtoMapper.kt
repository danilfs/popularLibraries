package com.example.gitapp.ui.users.data.api.mappers

interface DtoMapper<E, D> {
    fun mapToDomain(dtoEntity: E): D
}