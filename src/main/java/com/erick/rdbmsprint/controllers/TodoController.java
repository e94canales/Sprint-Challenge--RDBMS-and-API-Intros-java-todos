package com.erick.rdbmsprint.controllers;

import com.erick.rdbmsprint.models.Todos;
import com.erick.rdbmsprint.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    // http://localhost:2019/todos/user/5
    @PostMapping(value = "user/{id}", consumes = {"application/json"})
    public ResponseEntity<?> postToUser(@RequestBody Todos newTodo, @PathVariable long id){
        todoService.saveTodosToUser(newTodo, id);

        return new ResponseEntity<>("posted", HttpStatus.OK);
    }

    // http://localhost:2019/todos/todo/10
    @PatchMapping(value = "/todo/{id}", produces = {"application/json"})
    public ResponseEntity<?> markAsCompleted(@PathVariable long id){
        todoService.updateTodos(id);
        return new ResponseEntity<>("completed Todo with id: " + id, HttpStatus.OK);
    }
}
