/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import repositories.UserRepo;



@Named(value = "customer")
@SessionScoped
public class Customer implements Serializable {

    @Inject
    private UserRepo userRepo;
    
    
    private int customerID;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String phone;
    private int addressID;
    private String street;
    private String apartment;
    private String province;
    private String town;
    private String district;

    private String newPassword;
    private String newStreet;
    private String newApartment;
    private String newProvince;
    private String newTown;
    private String newDistrict;
    private String newPhone;

    public void init() throws SQLException {

        if (userRepo.getDataSource() == null) {
            throw new SQLException("Unable to obtain to Datasource");
        }

        Connection connection = userRepo.getDataSource().getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to Datasource");
        }
            try {
                PreparedStatement getCustomer = connection.prepareStatement("SELECT CUSTOMERID, FIRSTNAME, LASTNAME, PASSWORD, "
                        + "EMAIL, PHONENUMBER, ADDRESSID, STREET, APARTMENT, PROVINCE, TOWN, DISTRICT FROM CUSTOMER NATURAL JOIN ADDRESS "
                        + "WHERE EMAIL = ?");
                getCustomer.setString(1, userRepo.getEmail()); //test amaçlı customerID = 202 injectlendi userRepo, giriş yapılan email adresini alabiliriz
                ResultSet resultSet = getCustomer.executeQuery();
                while (resultSet.next()) {
                    customerID = resultSet.getInt("CUSTOMERID");
                    firstname = resultSet.getString("FIRSTNAME");
                    lastname = resultSet.getString("LASTNAME");
                    password = resultSet.getString("PASSWORD");
                    email = resultSet.getString("EMAIL");
                    phone = resultSet.getString("PHONENUMBER");
                    addressID = resultSet.getInt("ADDRESSID");
                    street = resultSet.getString("STREET");
                    apartment = resultSet.getString("APARTMENT");
                    province = resultSet.getString("PROVINCE");
                    town = resultSet.getString("TOWN");
                    district = resultSet.getString("DISTRICT");
                }
            } finally {
                connection.close();
            }
        }

    public String save() throws SQLException {
        init();

        if (userRepo.getDataSource() == null) {
            throw new SQLException("Unable to obtain to Datasource");
        }

        Connection connection = userRepo.getDataSource().getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to Datasource");
        }
        try {
            if (newPassword.length() > 0) {
                setPassword(newPassword);
                PreparedStatement updatePassword = connection.prepareStatement("UPDATE CUSTOMER SET PASSWORD = ? "
                        + "WHERE CUSTOMERID = ?");
                updatePassword.setString(1, getPassword());
                updatePassword.setInt(2, getCustomerID());
                updatePassword.executeUpdate();
            }

            if (newStreet.length() > 0 || newApartment.length() > 0 || newProvince.length() > 0 || newTown.length() > 0 || newDistrict.length() > 0) {
                setStreet(newStreet);
                setApartment(newApartment);
                setProvince(newProvince);
                setTown(newTown);
                setDistrict(newDistrict);
                PreparedStatement updateAddress = connection.prepareStatement("UPDATE ADDRESS SET STREET = ?, "
                        + "APARTMENT = ?, PROVINCE = ?, TOWN = ?, DISTRICT = ? WHERE ADDRESSID = ?");
                updateAddress.setString(1, getStreet());
                updateAddress.setString(2, getApartment());
                updateAddress.setString(3, getProvince());
                updateAddress.setString(4, getTown());
                updateAddress.setString(5, getDistrict());
                updateAddress.setInt(6, getAddressID());
                updateAddress.executeUpdate();
            }

            if (newPhone.length() > 0) {
                setPhone(newPhone);
                PreparedStatement updatePhone = connection.prepareStatement("UPDATE CUSTOMER SET PHONENUMBER = ? "
                        + "WHERE CUSTOMERID = ?");
                updatePhone.setString(1, getPhone());
                updatePhone.setInt(2, getCustomerID());
                updatePhone.executeUpdate();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Success", "Changes saved!"));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close(); // return this connection to pool

        } // end finally
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Success", "Changes saved!"));
        return "mainpage?faces-redirect=true";
        
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewStreet() {
        return newStreet;
    }

    public void setNewStreet(String newStreet) {
        this.newStreet = newStreet;
    }

    public String getNewApartment() {
        return newApartment;
    }

    public void setNewApartment(String newApartment) {
        this.newApartment = newApartment;
    }

    public String getNewProvince() {
        return newProvince;
    }

    public void setNewProvince(String newProvince) {
        this.newProvince = newProvince;
    }

    public String getNewTown() {
        return newTown;
    }

    public void setNewTown(String newTown) {
        this.newTown = newTown;
    }

    public String getNewDistrict() {
        return newDistrict;
    }

    public void setNewDistrict(String newDistrict) {
        this.newDistrict = newDistrict;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
}
