package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.service;

import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.dto.UserDto;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.entity.User;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.exception.UserNotFoundException;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.mapper.UserDtoUserMapper;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserDtoUserMapper userDtoUsermapper;

    UserService (UserRepository userRepository, UserDtoUserMapper userDtoUserMapper) {
        this.userRepository = userRepository;
        this.userDtoUsermapper = userDtoUserMapper;
    }

    private User findUserEntity(UUID id) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return foundUser.get();
    }

    public UserDto createUser(UserDto userDto) {
        User newUser = userDtoUsermapper.toUser(userDto);
        newUser.setCreatedAt(new Date().getTime());
        return userDtoUsermapper.toUserDto(
                userRepository.save(
                        newUser
                )
        );
    }

    public UserDto getUser(UUID id) throws UserNotFoundException {
        return userDtoUsermapper.toUserDto(this.findUserEntity(id));
    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtos;
        List<User> users = userRepository.findAll();
        userDtos = users.stream().map((user) -> userDtoUsermapper.toUserDto(user)).toList();
        return userDtos;
    }

    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
        User updatedUser = this.findUserEntity(userDto.getId());

        // Normally, converting UserDto to User and saving it would be enough.
        // But if you want to do only specific field updates this is the best way.
        updatedUser.setFirstName(userDto.getFirstName());
        updatedUser.setLastName(userDto.getLastName());
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setUpdatedAt(new Date().getTime());

        return userDtoUsermapper.toUserDto(
                userRepository.save(
                        updatedUser
                )
        );
    }

    public void deleteUser(UUID id) throws UserNotFoundException {
        this.findUserEntity(id);
        userRepository.deleteById(id);
    }
}
