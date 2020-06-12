package com.erick.rdbmsprint.services;

import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.views.UserTodoCount;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(long id);

    User saveUser(User user);

    void deleteUser(long id);

    List<UserTodoCount> getCountUsers();
}
