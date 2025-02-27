package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userServ = new UserServiceImpl();

        userServ.createUsersTable();

        userServ.saveUser("Nikita", "Skutin", (byte) 25);
        userServ.saveUser("Nikita", "Skutin", (byte) 26);
        userServ.saveUser("Nikita", "Skutin", (byte) 27);
        userServ.saveUser("Nikita", "Skutin", (byte) 28);

        List<User> users = userServ.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userServ.cleanUsersTable();

        userServ.dropUsersTable();
    }
}

