package com.erick.rdbmsprint.repositories;

import com.erick.rdbmsprint.models.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodosRepository extends CrudRepository<Todos, Long> {

}
