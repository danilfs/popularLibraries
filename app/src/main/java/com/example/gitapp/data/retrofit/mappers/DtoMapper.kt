package com.example.gitapp.data.retrofit.mappers

interface DtoMapper<E, D> {
 fun mapToDomain(dto: E): D
}