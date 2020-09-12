package com.tmjonker.food2u.entities.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewCustomerForm {

    @NotNull
    private String email;
    @NotNull
    @Size(min=8, max=30)
    private String password;
    @NotNull
    private String firstName;
    private String middleInitial;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    private String address2;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private Integer zipCode;
    private boolean exists;

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getPassword() {
        return password;
    }

    public String getState() {
        return state;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Customer toCustomer() {

        return new Customer(email, firstName, middleInitial, lastName, address, address2, city, state, zipCode);
    }
}
