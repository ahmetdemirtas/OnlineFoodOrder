/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.sql.DataSource;
import org.primefaces.event.FlowEvent;

@DataSourceDefinition(
        name = "java:global/onlinefoodorder/foodorder",
        className = "org.apache.derby.jdbc.ClientDataSource",
        url = "jdbc:derby://localhost:1527/foodorder",
        databaseName = "foodorder",
        user = "dedeler",
        password = "dedeler"
)

@Named(value = "customerEditBean")
@ViewScoped
public class CustomerEditBean implements Serializable {

    @ManagedProperty(value = "#{customer}")
    private Customer customer;

    private boolean skip;
    private String newPassword1;
    private String newPassword2;

    @Resource(lookup = "java:global/onlinefoodorder/foodorder")
    DataSource dataSource;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void save() throws SQLException {
        if (newPassword1 != null) {
            customer.setPassword(newPassword1);
        }

        if (dataSource == null) {
            throw new SQLException("Unable to obtain to Datasource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to Datasource");
        }

        try {
            PreparedStatement updateCustomer = connection.prepareStatement("UPDATE CUSTOMER SET PASSWORD = ?, "
                    + "PHONENUMBER = ? WHERE CUSTOMERID = ?; "
                    + "UPDATE ADDRESS SET STREET = ?, APARTMENT = ?, PROVINCE = ?, TOWN = ?, DISTRICT = ? WHERE ADDRESSID = ?");
            updateCustomer.setString(1, customer.getPassword());
            updateCustomer.setString(2, customer.getPhone());
            updateCustomer.setInt(3, customer.getCustomerID());
            updateCustomer.setString(4, customer.getStreet());
            updateCustomer.setString(5, customer.getApartment());
            updateCustomer.setString(6, customer.getProvince());
            updateCustomer.setString(7, customer.getTown());
            updateCustomer.setString(8, customer.getDistrict());
            updateCustomer.setInt(9, customer.getAddressID());
        } finally {
            connection.close();
        }
        FacesMessage msg = new FacesMessage("Success", "Changes saved!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case customer goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

}
