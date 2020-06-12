package com.erick.rdbmsprint.services;

import com.erick.rdbmsprint.models.Todos;
import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.repositories.TodosRepository;
import com.erick.rdbmsprint.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
            newUser.getTodos().add(t);
        }

        return userRepository.save(newUser);

    }
}
