///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import javax.annotation.Resource;
//import javax.annotation.sql.DataSourceDefinition;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
///**
// *
// * @author ahmetdemirtas
// */
//@DataSourceDefinition(
//        name = "java:global/onlinefoodorder/foodorder",
//        className = "org.apache.derby.jdbc.ClientDataSource",
//        url = "jdbc:derby://localhost:1527/foodorder",
//        databaseName = "foodorder",
//        user = "dedeler",
//        password = "dedeler"
//)
//
//public class ConnectionDB {
//
//    private Context initContext;
//
//    private static Context webContext;
//    
//    @Resource(lookup = "java:global/onlinefoodorder/foodorder")
//    static DataSource dataSource;
//    
//    private ConnectionDB() {
//        try {
//            initContext = new InitialContext();
//
//            webContext = (Context) initContext.lookup("java:/comp/env");
//
//            dataSource = (DataSource) webContext.lookup("java:global/onlinefoodorder/foodorder");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static DataSource getDS() {
//
//        if (dataSource == null) {
//            new ConnectionDB();
//        }
//
//        return dataSource;
//
//    }
//
//}
