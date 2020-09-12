package com.tmjonker.food2u.entities.customer;

import javax.validation.constraints.NotNull;

public class ReturningCustomerForm {

    @NotNull
    private String email;
    @NotNull
    private String password;
    private boolean alreadyExists;

    public boolean getAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
