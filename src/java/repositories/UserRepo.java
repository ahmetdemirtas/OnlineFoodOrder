/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Akın
 */
public class UserRepo {

    private String userId;
    private String password;
    private String email;

    
    DataSource dataSource;

    public UserRepo() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/foodorder");
        } catch (NamingException e) {
            e.printStackTrace();
        }
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

   /* public Boolean validateEmail(String email) {                                          //
        return getEmail().equals("asd@asd");                                                //
    }                                                               

    public Boolean validate(String email, String password) {                                // debug amaçlı
        return this.validateEmail(email) && this.validatePassword(password);
    }

    public Boolean validatePassword(String password) {                                      //
        return getPassword().equals("asd");
    } */ 

    public boolean validate(String email, String password) throws SQLException {
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
                    = connection.prepareStatement("select customer.email, customer.password"
                            + "where customer.email=? and customer.password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                tempEmail = rs.getString("email");
                tempPassword = rs.getString("password");
            }
            if (tempEmail.equals(email) && tempPassword.equals(password)) {
                return true;
            }
            return false;
        } // end try
        
        finally {
            connection.close(); // return this connection to pool

        } // end finally
    }
}
