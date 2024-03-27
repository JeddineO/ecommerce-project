package com.app.boutique.dto;

public class AdminDTO {

    private String email;
    private  String password;

    public AdminDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
