package security;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;

public class User implements Principal, Serializable {
    private String username;
    private String password;
    private String role;
    private static ArrayList<User> alleUsers = new ArrayList<>();

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        alleUsers.add(this);
    }

    @Override
    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void addUser(User user) {
        alleUsers.add(user);
    }

    public static ArrayList<User> getAlleUsers() {
        return alleUsers;
    }

    public static User getUserByName(String username) {
        for (User user : alleUsers) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static String validateLogin(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        User MyUser = User.getUserByName(username);
        if (MyUser == null) {
            return null;
        }
        if (MyUser.getPassword().equals(password)) {
            return MyUser.getRole();
        }
        return null;
    }
}
