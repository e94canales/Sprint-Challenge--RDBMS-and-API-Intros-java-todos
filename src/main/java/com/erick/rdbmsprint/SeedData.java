package com.erick.rdbmsprint;

import com.erick.rdbmsprint.models.Todos;
import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    UserRepository userService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception {
        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.getTodos()
                .add(new Todos("Give Joe access rights",
                        u1));
        u1.getTodos()
                .add(new Todos("Change the color of the home page",
                        u1));

        userService.save(u1);

        User u2 = new User("cinnamon",
                "1234567",
                "cinnamon@lambdaschool.local");
        u2.getTodos()
                .add(new Todos("Take a nap",
                        u2));
        u2.getTodos()
                .add(new Todos("Rearrange my hutch",
                        u2));
        u2.getTodos()
                .add(new Todos("Groom my fur",
                        u2));
        userService.save(u2);

        User u3 = new User("barnbarn",
                "ILuvM4th!",
                "barnbarn@lambdaschool.local");
        u3.getTodos()
                .add(new Todos("Rearrange my hutch",
                        u3));
        userService.save(u3);

        User u4 = new User("puttat",
                "password",
                "puttat@school.lambda");
        userService.save(u4);

        User u5 = new User("misskitty",
                "password",
                "misskitty@school.lambda");
        userService.save(u5);
    }
}
