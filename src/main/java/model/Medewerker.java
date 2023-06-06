package model;

public class Medewerker extends User {
    private String email;

    public Medewerker(String username, String password, String role, int id, String email) {
        super(username, password, role, id);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
