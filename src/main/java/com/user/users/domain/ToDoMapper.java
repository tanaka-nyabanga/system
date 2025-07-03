package com.user.users.domain;

import com.user.users.service.ToDoCommand;
import org.mapstruct.Mapper;

@Mapper
public interface ToDoMapper {
    ToDoDto toDto(ToDo toDo);

    ToDo toEntity(ToDoCommand toDoCommand);
}
