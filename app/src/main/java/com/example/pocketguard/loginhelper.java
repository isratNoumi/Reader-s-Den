package com.example.pocketguard;

public class loginhelper {
    private String email;
    private String pass;

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public loginhelper(String email,String pass) {
        this.email = email;
        this.pass= pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
