package com.kazakov.events.mainservice.dto.mappers;

import com.kazakov.events.mainservice.dto.CommentDto;
import com.kazakov.events.mainservice.dto.CommentNewDto;
import com.kazakov.events.mainservice.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CommentsMapper {
    @Mapping(target = "event", ignore = true)
    Comment commentNewDtoToComment(CommentNewDto commentNewDto);

    @Mapping(source = "event.id", target = "event")
    @Mapping(source = "author.id", target = "author")
    CommentDto commentToCommentDto(Comment comment);
}
