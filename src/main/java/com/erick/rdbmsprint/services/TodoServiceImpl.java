package com.erick.rdbmsprint.services;

import com.erick.rdbmsprint.models.Todos;
import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.repositories.TodosRepository;
import com.erick.rdbmsprint.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodosRepository todosRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public Todos saveTodosToUser(Todos todos, long id) {
        Todos newTodo = new Todos();
        User currentUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user with id " + id + " not found"));

        newTodo.setDescription(todos.getDescription());
        newTodo.setUser(currentUser);
        currentUser.getTodos().add(newTodo);

        return todosRepository.save(newTodo);
    }

    @Transactional
    @Override
    public Todos updateTodos(long id) {
        Todos currentTodo = todosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CANT FIND TODO ID TO UPDATE"));

       currentTodo.setCompleted(true);

        return todosRepository.save(currentTodo);
    }
}
