package com.user.users.service;

import com.user.users.domain.ToDoDto;

public interface ToDoService {
    ToDoDto create( ToDoCommand toDoCommand);
}
