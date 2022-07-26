package com.example.gitapp

interface DtoMapper<E, D> {
    fun mapToDomain(dto: E): D
}