package org.example;


import org.example.config.Config;
import org.example.model.User;
import org.example.service.Communication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.AnnotatedArrayType;
import java.util.List;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User user3 = new User(3L, "James", "Brown", (byte) 23);
        communication.saveUser(user3);

        User up = new User(3L, "Thomas", "Shelby", (byte) 21);
        communication.update(up);

        communication.deleteUser(3L);
    }
}