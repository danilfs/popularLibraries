package com.example.gitapp.data.retrofit.mappers

interface DtoMapper<E, D> {
 fun mapToDomain(dtoEntity: E): D
}