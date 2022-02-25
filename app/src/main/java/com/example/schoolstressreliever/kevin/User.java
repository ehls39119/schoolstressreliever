package com.example.schoolstressreliever.kevin;
import java.util.ArrayList;

public class User
{
    private String ID;
    private String name;
    private String email;
    private String password;
    private String yearLevel;
    public ArrayList userInfo = new ArrayList();

    public User(){}

    public User(String ID, String name, String email, String password, String yearLevel)
    {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.yearLevel = yearLevel;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public ArrayList getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ArrayList userInfo) {
        this.userInfo = userInfo;
    }
}
