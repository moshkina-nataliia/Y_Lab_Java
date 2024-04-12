package ru.ylab.trainingDiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class User {
    private String login;
    private String password;

    public boolean isAdmin() {
        return isAdmin;
    }

    private boolean isAdmin;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    private static User currentUser;

    public User(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private static ArrayList<User> users = new ArrayList<User>();
public static User currentUser(User user) {
    return user;
}
    public static boolean validateUser(String login, String password) {
        boolean isValid = false;
        for (User user : users) {
            if (user.getLogin().equals(login)
                    && user.getPassword().equals(password)) {
                isValid = true;
                setCurrentUser(user);

                break;
            }
        }
        return isValid;
    }

    public static void register()  {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean loginFree = true;
        System.out.println("Регистрация");
        System.out.println();
        System.out.println("Введите логин");
        String newLogin = reader.readLine();
        System.out.println("Введите пароль");
        String newPassword = reader.readLine();

        for (User user1 : users) {
            if (user1.getLogin().equals(newLogin)) {
                loginFree = false;
                break;
            }
        }
        if (loginFree) {
            User newUser = new User(newLogin, newPassword, false);
            users.add(newUser);
            System.out.println("Вы зарегистрировались");
            System.out.println("---------------------");
        } else {
            System.out.println("Пользователь с таким логином уже существует. Придумайте другой.");
            System.out.println();
        }} catch (IOException e) {
            System.out.println("Неверный ввод");
            System.out.println();
        }
    }

    public static void initAdmin() {
        User admin = new User("admin", "admin", true);
        users.add(admin);
    }

}


