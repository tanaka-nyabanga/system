package com.user.users.domain;

import com.user.users.service.ToDoCommand;
import com.user.users.service.ToDoService;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    ToDoServiceImpl(ToDoRepository toDoRepository, ToDoMapper toDoMapper) {
        this.toDoRepository = toDoRepository;
        this.toDoMapper = toDoMapper;
    }

    @Override
    public ToDoDto create(ToDoCommand toDoCommand) {

        ToDo toDo = toDoMapper.toEntity(toDoCommand);
        return toDoMapper.toDto(toDoRepository.save(toDo));
    }
}
