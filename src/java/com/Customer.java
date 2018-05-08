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
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

@DataSourceDefinition(
        name = "java:global/onlinefoodorder/foodorder",
        className = "org.apache.derby.jdbc.ClientDataSource",
        url = "jdbc:derby://localhost:1527/foodorder",
        databaseName = "foodorder",
        user = "dedeler",
        password = "dedeler"
)

@Named(value = "customer")
@SessionScoped
public class Customer implements Serializable {

    @Resource(lookup = "java:global/onlinefoodorder/foodorder")
    DataSource dataSource;
    
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

    public void customerSetupFromDB() throws SQLException{
        if (dataSource == null) {
            throw new SQLException("Unable to obtain to Datasource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to Datasource");
        }

        try {
            PreparedStatement getCustomer = connection.prepareStatement("SELECT * " +
                    "FROM (SELECT CUSTOMERID, FIRSTNAME, LASTNAME, PASSWORD, EMAIL, PHONENUMBER, ADDRESSID, STREET, " + 
                    "APARTMENT, PROVINCE, TOWN, DISTRICT FROM CUSTOMER INNER JOIN ADDRESS WHERE CUSTOMER.ADDRESSID = ADDRESS.ADDRESSID) " +
                    "WHERE CUSTOMERID = ?");
            getCustomer.setInt(1, 202); //test amaçlı customerID = 202
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
