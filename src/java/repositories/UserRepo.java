/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@DataSourceDefinition(
        name = "java:global/onlinefoodorder/foodorder",
        className = "org.apache.derby.jdbc.ClientDataSource",
        url = "jdbc:derby://localhost:1527/foodorder",
        databaseName = "foodorder",
        user = "dedeler",
        password = "dedeler"
)

/**
 *
 * @author Akın
 */
@Named(value = "userRepo")
@SessionScoped
public class UserRepo implements Serializable {

    private String userId;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String addressId;
    private String phoneNumber;
    private String wallet;
    @Resource(lookup = "java:global/onlinefoodorder/foodorder")
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    
    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean validate() throws SQLException {
        // check whether dataSource was injected by the server
        String tempEmail = null;
        String tempPassword = null;
        ResultSet rs = null;
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            // create a PreparedStatement to insert a new address book entry
            PreparedStatement ps
                    = connection.prepareStatement("select email, password from customer "
                            + "where email=? and password=?");
            ps.setString(1, getEmail());
            ps.setString(2, getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                tempEmail = rs.getString("email");
                tempPassword = rs.getString("password");
            }
            if (tempEmail.equalsIgnoreCase(email) && tempPassword.equals(password)) {
                FacesContext context = FacesContext.getCurrentInstance();

                context.getExternalContext().getSessionMap().put("user", tempEmail);
                return true;
            }
            return false;
        } // end try
        finally {
            connection.close(); // return this connection to pool

        } // end finally
    }

    public String nextPage() {
        try {

            if (validate()) {
                save();                
                return "mainpage?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "signin?faces-redirect=true";

    }

    public void save() throws SQLException {
        ResultSet rs = null;
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            // create a PreparedStatement to insert a new address book entry
            PreparedStatement ps
                    = connection.prepareStatement("select firstname, lastname, customerid, addressid, phonenumber, wallet, email from customer "
                            + "where email=? and password=?");
            ps.setString(1, getEmail());
            ps.setString(2, getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                userId = rs.getString("customerid");
                addressId = rs.getString("addressid");
                phoneNumber = rs.getString("phonenumber");
                wallet = rs.getString("wallet");
                email = rs.getString("email");

            }

        } // end try // end try
        finally {
            connection.close(); // return this connection to pool

        } // end finally
    }

}
