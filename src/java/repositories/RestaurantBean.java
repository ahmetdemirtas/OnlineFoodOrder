/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Akın
 */
@Named(value = "restaurantBean")
@SessionScoped
public class RestaurantBean implements Serializable {

    private List<String> foodList;
    private List<String> orderList;
    private List<Integer> priceList;
    private String foodOne;
    private String foodTwo;
    private String foodThree;
    private String foodFour;
    private String foodFive;
    private String foodSix;
    private int priceOne;
    private int priceTwo;    
    private int priceThree;
    private int priceFour;
    private int priceFive;
    private int priceSix;
    private int orderTotal;
    
   public String addOrderOne(){
       orderList.add(foodOne);
       orderTotal += priceOne;
       return "";
   }
   
    public String addOrderTwo() {
        orderList.add(foodTwo);
        orderTotal += priceTwo;
        return "";
    }

    public String addOrderThree() {
        orderList.add(foodThree);
        orderTotal += priceThree;
        return "";
    }

    public String addOrderFour() {
        orderList.add(foodFour);
        orderTotal += priceFour;
        return "";
    }

    public String addOrderFive() {
        orderList.add(foodFive);
        orderTotal += priceFive;
        return "";
    }

    public String addOrderSix() {
        orderList.add(foodSix);
        orderTotal += priceSix;
        return "";
    }

    public String resPage(int resId) throws SQLException {
        Connection conn;
        String resName = null;
        foodList = new ArrayList<>();
        priceList = new ArrayList<>();
        try {
            ResultSet rs = null;
           
            orderList = new ArrayList<>();
 
            // obtain a connection from the connection pool
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/foodorder", "dedeler", "dedeler");

            // check whether connection was successful
            if (conn == null) {
                throw new SQLException("Unexpected error");
            }
            PreparedStatement ps
                    = conn.prepareStatement("SELECT FOODNAME, RESTAURANTNAME, PRICE FROM RESTAURANT NATURAL JOIN FOOD "
                            + "WHERE RESTAURANTID = ?");
            ps.setInt(1, resId);
            rs = ps.executeQuery();
            while (rs.next()) {
                foodList.add(rs.getString("FOODNAME"));
                priceList.add(rs.getInt("PRICE"));
                resName = rs.getString("RESTAURANTNAME");
            }
            foodOne = foodList.get(0);
            foodTwo = foodList.get(1);
            foodThree = foodList.get(2);
            foodFour = foodList.get(3);
            foodFive = foodList.get(4);
            foodSix = foodList.get(5);
            priceOne = priceList.get(0);
            priceTwo = priceList.get(1);
            priceThree = priceList.get(2);
            priceFour = priceList.get(3);
            priceFive = priceList.get(4);
            priceSix = priceList.get(5);
            conn.close();
        } finally {
            resName = resName.concat("?faces-redirect=true");
            return resName;
        }

    }
    
    //getters and setters

    public List<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<String> foodList) {
        this.foodList = foodList;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }

    public String getFoodOne() {
        return foodOne;
    }

    public void setFoodOne(String foodOne) {
        this.foodOne = foodOne;
    }

    public String getFoodTwo() {
        return foodTwo;
    }

    public void setFoodTwo(String foodTwo) {
        this.foodTwo = foodTwo;
    }

    public String getFoodThree() {
        return foodThree;
    }

    public void setFoodThree(String foodThree) {
        this.foodThree = foodThree;
    }

    public String getFoodFour() {
        return foodFour;
    }

    public void setFoodFour(String foodFour) {
        this.foodFour = foodFour;
    }

    public String getFoodFive() {
        return foodFive;
    }

    public void setFoodFive(String foodFive) {
        this.foodFive = foodFive;
    }

    public String getFoodSix() {
        return foodSix;
    }

    public void setFoodSix(String foodSix) {
        this.foodSix = foodSix;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Integer> priceList) {
        this.priceList = priceList;
    }

    public int getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(int priceOne) {
        this.priceOne = priceOne;
    }

    public int getPriceTwo() {
        return priceTwo;
    }

    public void setPriceTwo(int priceTwo) {
        this.priceTwo = priceTwo;
    }

    public int getPriceThree() {
        return priceThree;
    }

    public void setPriceThree(int priceThree) {
        this.priceThree = priceThree;
    }

    public int getPriceFour() {
        return priceFour;
    }

    public void setPriceFour(int priceFour) {
        this.priceFour = priceFour;
    }

    public int getPriceFive() {
        return priceFive;
    }

    public void setPriceFive(int priceFive) {
        this.priceFive = priceFive;
    }

    public int getPriceSix() {
        return priceSix;
    }

    public void setPriceSix(int priceSix) {
        this.priceSix = priceSix;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    

}
