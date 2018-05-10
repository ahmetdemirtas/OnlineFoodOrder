/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author Akın
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import repositories.UserRepo;

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
@Named(value="mainPage")
@SessionScoped
public class MainPage implements Serializable{
    
    @Inject
    private UserRepo userRepo;
     
    
    
    
    
    
    //Getters and Setters
    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

  
}
