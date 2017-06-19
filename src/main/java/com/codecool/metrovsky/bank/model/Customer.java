package com.codecool.metrovsky.bank.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;


public class Customer extends BaseModel {

    private final String login;
    private final Timestamp createDate;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isActive;
    private Timestamp lastLogin;
    private boolean active;

    public Customer(Integer id, String firstName, String lastName, String login, String password, Timestamp createDate,
                    boolean isActive, Timestamp lastLogin) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
    }

    public Customer(String firstName, String lastName, String login, String password, Timestamp createDate,
                    boolean isActive) throws NoSuchAlgorithmException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = this.createHash(password);
        this.createDate = createDate;
        this.isActive = isActive;
    }

    public String createHash(String password) throws NoSuchAlgorithmException {
        return this.hashPassword(password);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] digest = md.digest();

        return this.convertByteToHex(digest);

    }

    private String convertByteToHex(byte[] byteData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreateDate() {
        return createDate;

    }

    public boolean isActive() {
        return isActive;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}



