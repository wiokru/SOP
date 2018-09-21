package users;

import java.util.Arrays;

public abstract class User {

    private String id;
    private String name;
    private String surname;
    private String email;
    private char[] password;

    public boolean checkPassword (char[] passw){
        return (Arrays.equals(passw, password));
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return  name +" "+ surname + " email: " + email;
    }
}
