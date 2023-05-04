package com.kazakov.eventkeeper.mainservice.services.impl;

import com.kazakov.eventkeeper.mainservice.model.User;
import com.kazakov.eventkeeper.mainservice.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kazakov.eventkeeper.mainservice.dao.UserRepository;
import com.kazakov.eventkeeper.mainservice.dto.UserDto;
import com.kazakov.eventkeeper.mainservice.dto.UserShortDto;
import com.kazakov.eventkeeper.mainservice.dto.mappers.UserMapper;
import com.kazakov.eventkeeper.mainservice.exceptions.UserNotFoundException;
import com.kazakov.eventkeeper.mainservice.services.UserService;

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
        if (size == null) {
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
        user.setRole(userShortDto.getAdmin() ? Roles.ROLE_ADMIN : Roles.ROLE_USER);
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
