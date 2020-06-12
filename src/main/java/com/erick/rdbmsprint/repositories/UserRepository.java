package com.erick.rdbmsprint.repositories;

import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.views.UserTodoCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u.username AS usernames, count(td.userid) AS counttodos FROM users u LEFT JOIN todos td ON u.userid = td.userid GROUP BY u.username", nativeQuery = true)
    List<UserTodoCount> getCountUsers();
}
