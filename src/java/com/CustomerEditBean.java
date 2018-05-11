///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com;
//
//import javax.inject.Named;
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import javax.annotation.Resource;
//import javax.annotation.sql.DataSourceDefinition;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.annotation.ManagedProperty;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.sql.DataSource;
//import org.primefaces.event.FlowEvent;
//
//@DataSourceDefinition(
//        name = "java:global/onlinefoodorder/foodorder",
//        className = "org.apache.derby.jdbc.ClientDataSource",
//        url = "jdbc:derby://localhost:1527/foodorder",
//        databaseName = "foodorder",
//        user = "dedeler",
//        password = "dedeler"
//)
//
//@Named(value = "customerEditBean")
//@ViewScoped
//public class CustomerEditBean implements Serializable {
//
//    private Customer customer;
//
//    private String newPassword;
//    private String newStreet;
//    private String newApartment;
//    private String newProvince;
//    private String newTown;
//    private String newDistrict;
//    private String newPhone;
//
//    @Resource(lookup = "java:global/onlinefoodorder/foodorder")
//    DataSource dataSource;
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public void save() throws SQLException {
//        customer = new Customer();
//        customer.init();
//
//        if (dataSource == null) {
//            throw new SQLException("Unable to obtain to Datasource");
//        }
//
//        Connection connection = dataSource.getConnection();
//
//        if (connection == null) {
//            throw new SQLException("Unable to connect to Datasource");
//        }
//
//        try {
//            if (newPassword.length() > 0) {
//                customer.setPassword(newPassword);
//                PreparedStatement updatePassword = ConnectionDB.getDS().getConnection().prepareStatement("UPDATE CUSTOMER SET PASSWORD = ?, "
//                        + "WHERE CUSTOMERID = ?");
//                updatePassword.setString(1, customer.getPassword());
//                updatePassword.setInt(2, customer.getCustomerID());
//            }
//
//            if (newStreet.length() > 0 || newApartment.length() > 0 || newProvince.length() > 0 || newTown.length() > 0 || newDistrict.length() > 0) {
//                customer.setStreet(newStreet);
//                customer.setApartment(newApartment);
//                customer.setProvince(newProvince);
//                customer.setTown(newTown);
//                customer.setDistrict(newDistrict);
//                PreparedStatement updateAddress = ConnectionDB.getDS().getConnection().prepareStatement("UPDATE ADDRESS SET STREET = ?, "
//                        + "APARTMENT = ?, PROVINCE = ?, TOWN = ?, DISTRICT = ? WHERE ADDRESSID = ?");
//                updateAddress.setString(1, customer.getStreet());
//                updateAddress.setString(2, customer.getApartment());
//                updateAddress.setString(3, customer.getProvince());
//                updateAddress.setString(4, customer.getTown());
//                updateAddress.setString(5, customer.getDistrict());
//                updateAddress.setInt(6, customer.getAddressID());
//            }
//
//            if (newPhone.length() > 0) {
//                customer.setPhone(newPhone);
//                PreparedStatement updatePhone = ConnectionDB.getDS().getConnection().prepareStatement("UPDATE CUSTOMER SET PHONENUMBER = ?, "
//                        + "WHERE CUSTOMERID = ?");
//                updatePhone.setString(1, customer.getPhone());
//                updatePhone.setInt(2, customer.getCustomerID());
//            }
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Success", "Changes saved!"));
//    }
//
//    public String getNewPassword() {
//        return newPassword;
//    }
//
//    public void setNewPassword(String newPassword) {
//        this.newPassword = newPassword;
//    }
//
//    public String getNewStreet() {
//        return newStreet;
//    }
//
//    public void setNewStreet(String newStreet) {
//        this.newStreet = newStreet;
//    }
//
//    public String getNewApartment() {
//        return newApartment;
//    }
//
//    public void setNewApartment(String newApartment) {
//        this.newApartment = newApartment;
//    }
//
//    public String getNewProvince() {
//        return newProvince;
//    }
//
//    public void setNewProvince(String newProvince) {
//        this.newProvince = newProvince;
//    }
//
//    public String getNewTown() {
//        return newTown;
//    }
//
//    public void setNewTown(String newTown) {
//        this.newTown = newTown;
//    }
//
//    public String getNewDistrict() {
//        return newDistrict;
//    }
//
//    public void setNewDistrict(String newDistrict) {
//        this.newDistrict = newDistrict;
//    }
//
//    public String getNewPhone() {
//        return newPhone;
//    }
//
//    public void setNewPhone(String newPhone) {
//        this.newPhone = newPhone;
//    }
//
//}
