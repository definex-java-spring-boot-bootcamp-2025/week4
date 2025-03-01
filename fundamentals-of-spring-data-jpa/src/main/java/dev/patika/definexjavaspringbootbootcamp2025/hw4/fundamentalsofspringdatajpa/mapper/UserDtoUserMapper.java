package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.mapper;

import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.dto.UserDto;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoUserMapper {

    UserDtoUserMapper MAPPER = Mappers.getMapper(UserDtoUserMapper.class);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
