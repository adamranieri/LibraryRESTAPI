package dev.ranieri.dtos;

// A Data Transfer Object is designed to just be a vessel to hold a JSON going between frontend and backend
// minimal logic. This class DOES NOT represent a table in the database like an entity
public class LoginCredentials {

    private String username;
    private String password;

    public LoginCredentials() {
    }

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
