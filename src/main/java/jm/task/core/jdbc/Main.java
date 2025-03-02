package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;

public class Main {
    public static void main(String[] args) {

        UserService userServ = new UserServiceHibernateImpl();

        userServ.createUsersTable();

        userServ.saveUser("Nikita", "Skutin", (byte) 25);
        userServ.saveUser("Nikita", "Skutin", (byte) 26);
        userServ.saveUser("Nikita", "Skutin", (byte) 27);
        userServ.saveUser("Nikita", "Skutin", (byte) 28);

        userServ.getAllUsers().forEach(System.out::println);



        userServ.cleanUsersTable();
        userServ.dropUsersTable();
    }
}

