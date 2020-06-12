package com.erick.rdbmsprint.services;

import com.erick.rdbmsprint.models.Todos;
import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.repositories.TodosRepository;
import com.erick.rdbmsprint.repositories.UserRepository;
import com.erick.rdbmsprint.views.UserTodoCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    TodosRepository todosRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> rtnList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        User newUser = new User();

        // PUT
        if (user.getUserid() != 0) {
            userRepository.findById(user.getUserid()).orElseThrow(() -> new EntityNotFoundException(user.getUserid() + " not found for PUT request"));

            newUser.setUserid(user.getUserid());
        }


        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());

        newUser.getTodos().clear();
        for (Todos t : user.getTodos()){

            Todos newTodo = new Todos();
            newTodo.setDescription(t.getDescription());
            newTodo.setUser(newUser);

            newUser.getTodos().add(newTodo);
        }

        return userRepository.save(newUser);

    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("USER ID NOT FOUND");
        }
    }

    @Override
    public List<UserTodoCount> getCountUsers() {
        return userRepository.getCountUsers();
    }
}
