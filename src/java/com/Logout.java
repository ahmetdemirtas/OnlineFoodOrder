/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Akın
 */
@Named(value="logout")
@SessionScoped
public class Logout implements Serializable{
  
    
    public String logout() {
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "signin?faces-redirect=true";
    }

}
