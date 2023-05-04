package com.kazakov.eventkeeper.mainservice.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.eventkeeper.mainservice.dto.UserDto;
import com.kazakov.eventkeeper.mainservice.dto.UserShortDto;
import com.kazakov.eventkeeper.mainservice.model.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto userToUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User userShortDtoToUser(UserShortDto userShortDto);
}
