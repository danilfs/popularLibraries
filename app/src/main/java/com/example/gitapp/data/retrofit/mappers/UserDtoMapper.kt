package com.example.gitapp




class UserDtoMapper : DtoMapper<UserDto, User> {
    override fun mapToDomain(dto: UserDto) = User(
        dto.id,
        dto.login,
        dto.avatarUrl.orEmpty(),
    )
}