package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Ванюша", "ЛучшийПрогер", (byte) 30);
        usi.saveUser("Kata", "Academy", (byte) 120);
        usi.saveUser("Виктория", "Клешнева", (byte) 30);
        usi.saveUser("Illon", "Mask", (byte) 30);
        usi.getAllUsers();
        usi.cleanUsersTable();
        usi.dropUsersTable();

    }
}
