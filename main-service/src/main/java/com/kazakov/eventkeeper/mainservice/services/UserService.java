package com.kazakov.eventkeeper.mainservice.services;

import org.springframework.stereotype.Service;
import com.kazakov.eventkeeper.mainservice.dto.UserDto;
import com.kazakov.eventkeeper.mainservice.dto.UserShortDto;
import com.kazakov.eventkeeper.mainservice.model.User;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> findAllUsers(List<Long> ids, Integer from, Integer size);

    List<UserDto> findAllUsersByRole(String role);

    UserDto createUser(UserShortDto userShortDto);

    void deleteUser(Long id);

    User findByName(String username);
}
