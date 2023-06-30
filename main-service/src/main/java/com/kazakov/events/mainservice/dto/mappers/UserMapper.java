package com.kazakov.events.mainservice.dto.mappers;

import com.kazakov.events.mainservice.dto.UserDto;
import com.kazakov.events.mainservice.dto.UserShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.events.mainservice.model.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto userToUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User userShortDtoToUser(UserShortDto userShortDto);
}
