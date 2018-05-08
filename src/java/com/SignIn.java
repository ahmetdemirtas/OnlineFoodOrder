/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import repositories.UserRepo;

/**
 *
 * @author Akın
 */
@ManagedBean(name = "signIn")
@SessionScoped
public class SignIn {

    private String email, password;
    private UserRepo userRepo;

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        userRepo.setEmail(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        userRepo.setPassword(password);
    }

    /**
     * Creates a new instance of SignIn
     */
    public SignIn() {

    }

    public void onPageLoad() {
        userRepo = new UserRepo();
    }

    public String nextPage() {
        try {

            if (getUserRepo().validate(email, password)) {
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "signin";

    }

    //signin doğrulaması
}
