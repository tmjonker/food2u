package com.tmjonker.food2u.forms;

import com.tmjonker.food2u.entities.restaurant.Restaurant;
import com.tmjonker.food2u.entities.user.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewRestaurantForm {

    @NotNull
    @Size(min=5)
    private String name;
    @NotNull
    @Size(min=5)
    private String address;
    private String address2;
    @NotNull
    @Size(min=2)
    private String city;
    @NotNull
    @Size(min=2)
    private String state;
    @NotNull
    @Pattern(regexp="\\d{5,}")
    private String zipCode;
    @NotNull
    @Size(min=12)
    @Pattern(regexp="^\\d{3}-\\d{3}-\\d{4}$")
    private String phoneNumber;
    @NotNull
    @Size(min=2)
    private String category;
    @NotNull
    @Size(min=2)
    private String imgPath;
    private boolean alreadyExists;

    public NewRestaurantForm() {

    }

    public Restaurant toRestaurant() {
        return new Restaurant(name, address, address2, city, state, zipCode, phoneNumber, category, imgPath);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
