package com.tmjonker.food2u.entities.user;

import javax.validation.constraints.NotNull;

public class ReturningUserForm {

    @NotNull
    private String email;
    private String password;
    private boolean alreadyExists;

    public ReturningUserForm(String email, String password, boolean alreadyExists) {

        this.email = email;
        this.password = password;
        this.alreadyExists = alreadyExists;
    }

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
