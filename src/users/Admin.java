package users;

import java.util.Arrays;

public class Admin {

    private static Admin ourInstance = new Admin();
    private String login;
    private char[] password;


    public static Admin getInstance() {
        if(ourInstance == null)
            ourInstance = new Admin();
        return ourInstance;
    }

    private Admin() {
        this.login = "admin";
        this.password = "admin".toCharArray();
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword (char[] passw){
        return (Arrays.equals(passw, password));
    }
}
