package com.kazakov.events.mainservice.services;

import com.kazakov.events.mainservice.dto.UserDto;
import com.kazakov.events.mainservice.dto.UserShortDto;
import org.springframework.stereotype.Service;
import com.kazakov.events.mainservice.model.User;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> findAllUsers(List<Long> ids, Integer from, Integer size);

    List<UserDto> findAllUsersByRole(String role);

    UserDto createUser(UserShortDto userShortDto);

    void deleteUser(Long id);

    User findByName(String username);
}
