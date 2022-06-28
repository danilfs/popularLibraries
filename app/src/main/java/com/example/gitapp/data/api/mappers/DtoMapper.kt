package com.example.gitapp.data.api.mappers

interface DtoMapper<E, D> {
    fun mapToDomain(dtoEntity: E): D
}