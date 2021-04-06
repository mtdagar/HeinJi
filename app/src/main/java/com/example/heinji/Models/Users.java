package com.example.heinji.Models;

public class Users {

    String profilepic;
    String userName;
    String mail;

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    String password;

    public Users(String profilepic, String userName, String mail, String password, String userID, String lastMessage) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userID = userID;
        this.lastMessage = lastMessage;
    }

    String userID;
    String lastMessage;

}
