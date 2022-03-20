package com.tmjonker.food2u.entities.user;

public class ChangePasswordForm {

    private String username;
    private String password1;
    private String password2;
    private boolean passwordsMatch;
    private boolean passwordChanged;

    public ChangePasswordForm(String username, String password1, String password2) {

        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        passwordsMatch = true;
        passwordChanged = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }


    public String getPassword1() {
        return password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


    public String getPassword2() {
        return password2;
    }

    public void setPasswordsMatch(boolean passwordsMatch) {
        this.passwordsMatch = passwordsMatch;
    }

    public boolean getPasswordsMatch() {
        return passwordsMatch;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public boolean getPasswordChanged() {
        return passwordChanged;
    }
}
