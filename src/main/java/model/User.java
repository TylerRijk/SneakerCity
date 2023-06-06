package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import persistence.PersistenceKlant;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;

public class User implements Principal, Serializable {
    protected String username;
    protected String password;
    protected String role;
    protected int id;
    public static ArrayList<User> alleUsers = new ArrayList<>();

    public User(String username, String password, String role, int id) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
        alleUsers.add(this);
    }

    @Override
    public String getName() {
        return username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
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

    public static int generateId() {
        int highestId = 0;
        for (User user : alleUsers) {
            if (user.getId() > highestId) {
                highestId = user.getId();
            }
        }
        return highestId + 1;
    }

    public static void loadUsersFromFiles() {
        ArrayList<Klant> klanten = PersistenceKlant.loadAllKlanten();
        for (Klant klant : klanten) {
            User user = new User(klant.getName(), klant.getPassword(), klant.getRole(), klant.getId());
            alleUsers.add(user);
        }
    }
}
