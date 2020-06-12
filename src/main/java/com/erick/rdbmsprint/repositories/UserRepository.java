package com.erick.rdbmsprint.repositories;

import com.erick.rdbmsprint.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
