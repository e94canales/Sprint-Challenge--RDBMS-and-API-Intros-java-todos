package com.erick.rdbmsprint.services;

import com.erick.rdbmsprint.models.Todos;

public interface TodoService {
    Todos saveTodosToUser(Todos todos, long id);

    Todos updateTodos(long id);

}
