package com.user.users.api;

import com.user.users.domain.ToDoDto;
import com.user.users.service.ToDoCommand;
import com.user.users.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
@Tag(name = "Task APIs",description = "API Documentation for Tasks")
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/create")
    @Operation (summary = "Create Task",description = "API to create a task")
    public ToDoDto create(@Valid @RequestBody ToDoCommand toDoCommand){
       return toDoService.create(toDoCommand);

    }
}
