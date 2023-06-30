package com.kazakov.events.mainservice.services.impl;

import com.kazakov.events.mainservice.model.User;
import com.kazakov.events.mainservice.security.Roles;
import com.kazakov.events.mainservice.dto.UserDto;
import com.kazakov.events.mainservice.dto.UserShortDto;
import com.kazakov.events.mainservice.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kazakov.events.mainservice.dao.UserRepository;
import com.kazakov.events.mainservice.dto.mappers.UserMapper;
import com.kazakov.events.mainservice.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAllUsers(List<Long> ids, Integer from, Integer size) {
        Pageable pageable;
        if (size == null || size.equals(0)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE);
        } else {
            pageable = PageRequest.of(from / size, size);
        }
        if (ids != null) {
            return userRepository.findByIdIn(ids, pageable)
                    .stream()
                    .map(userMapper::userToUserDto)
                    .collect(Collectors.toList());
        } else {
            return userRepository.findAll(pageable)
                    .stream()
                    .map(userMapper::userToUserDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<UserDto> findAllUsersByRole(String role) {
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserShortDto userShortDto) {
        User user = userMapper.userShortDtoToUser(userShortDto);
        user.setPassword(passwordEncoder.encode(userShortDto.getPassword()));
        user.setRole(userShortDto.isAdmin() ? Roles.ROLE_ADMIN : Roles.ROLE_USER);
        return userMapper.userToUserDto(
                userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with name=%s was not found", username)));
    }

    private void findUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                    String.format("User with id=%d was not found", id)));
    }
}
