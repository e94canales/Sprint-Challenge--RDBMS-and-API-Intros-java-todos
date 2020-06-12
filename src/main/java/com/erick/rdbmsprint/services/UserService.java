package com.erick.rdbmsprint.services;

import com.erick.rdbmsprint.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(long id);

    User saveUser(User user);
}
