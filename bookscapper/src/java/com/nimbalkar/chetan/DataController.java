package com.nimbalkar.chetan;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;

        
public class DataController {

   private final String DB_NAME = "books.db";
   private final String CONNECTION_STRING = "jdbc:sqlite:/home/chetan/NetBeansProjects/bookscapper/DataModel/" + DB_NAME;
   private final String DRIVER  = "org.sqlite.JDBC";
   private  Connection connection = null;
   private final String TABLE_VISITOR = "visitor";
   private final String COLUMN_VISITOR_ID = "_id";
   
   private final String TABLE_CATEGORIES = "categories";
   private final String COLUMN_CATEGORY_ID = "_id";
   private final String COLUMN_CATEGORY_NAME = "name";
   
   private final String TABLE_BOOKS = "books";
   private final String COLUMN_BOOK_ID = "_id";
   private final String COLUMN_BOOK_CATEGORY_ID = "category_id";
   private final String COLUMN_BOOK_NAME = "name";
   private final String COLUMN_BOOK_PRICE = "price";
   private final String COLUMN_BOOK_CONDITION = "condition";

   private final String TABLE_USERS = "users";
   private final String COLUMN_USER_ID = "_id";
   private final String COLUMN_USER_FIRST_NAME = "first_name";
   private final String COLUMN_USER_LAST_NAME = "last_name";
   private final String COLUMN_USER_NAME = "username";
   private final String COLUMN_USER_PASSWORD = "password";

   private final String TABLE_CARTS = "carts";
   private final String COLUMN_CART_VISITOR_ID = "visitor_id";
   private final String COLUMN_CART_BOOK_ID = "book_id";
   

   private final String TABLE_SELL = "sell";
   private final String COLUMN_SELL_BOOK_ID = "book_id";
   private final String COLUMN_SELL_SELL_TIME = "sell_time";

   private final String TABLE_PURCHASE = "purchase";
   private final String COLUMN_PURCHASE_BOOK_ID = "book_id";
   private final String COLUMN_PURCHASE_PURCHASE_TIME = "purchase_time";


   public boolean open() throws SQLException{
       try {
           Class.forName(DRIVER);
           connection = DriverManager.getConnection(CONNECTION_STRING);
           return true;
       } catch(SQLException | ClassNotFoundException e) {
           return false;
       }
   }
   
   public void close() throws SQLException{
       if(connection != null) {
           connection.close();
       }
   }
   
   public void storeUser(String fName, String lName, String uName, String passwd) {
       try{
            open();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USER_FIRST_NAME + "," + COLUMN_USER_LAST_NAME + "," + COLUMN_USER_NAME + "," + COLUMN_USER_PASSWORD + ")" + " VALUES('" + fName + "', '" + lName + "', '" + uName + "', '" + passwd + "')");            
            close();
       } catch(Exception  e) {
           e.printStackTrace();
       }
   }
   
   public boolean validate(String uName, String passwd) {
       String pass = null;
       int id = 0;
       try {
           open();
           Statement statement = connection.createStatement();
           ResultSet rs = statement.executeQuery(" SELECT  *  FROM  " + TABLE_USERS + " WHERE " + COLUMN_USER_NAME + "=" + "'" + uName + "'");           
           if(rs.next()){
              id = rs.getInt(COLUMN_USER_ID);
              pass = rs.getString(COLUMN_USER_PASSWORD);
           }

       } catch(SQLException e) {
            e.printStackTrace();
       } 
        if(pass.equals(passwd)) {
            User.setUserID(id);
            return true;
        } else {
            return false;
        }   
   }
   
   
   public void getRecents(ResultSet resultset1, Statement statement1, Statement statement2, Statement statement3, List<Book> recents) throws SQLException {
       if(resultset1 != null) {
         while(resultset1.next()) {
            ResultSet resultset2 = statement2.executeQuery(" SELECT  *  FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_ID + "=" + "'" + resultset1.getInt(COLUMN_SELL_BOOK_ID) + "'"); 
            while(resultset2.next()) {
                ResultSet resultset3 = statement3.executeQuery(" SELECT  *  FROM " + TABLE_CATEGORIES + " WHERE " + COLUMN_CATEGORY_ID + "=" + "'" + resultset2.getInt(COLUMN_BOOK_CATEGORY_ID) + "'");
                String category = "";
                while(resultset3.next()) {
                  category = resultset3.getString(COLUMN_CATEGORY_NAME);
                }
                if (resultset2 != null) {   
                    recents.add(new Book(category, resultset2.getString(COLUMN_BOOK_NAME), resultset2.getFloat(COLUMN_BOOK_PRICE), resultset2.getString(COLUMN_BOOK_CONDITION)));
                }
            }          
         }        
       }       
   }

   public List<Book> getRecentBooks() throws SQLException {
       open();
       List<Book> recents = new ArrayList<>();
       Statement statement1 = null;
       Statement statement2 = null;
       Statement statement3 = null;
       Statement statement4 = null;
       ResultSet resultset1 = null;
       try {
             statement1 = connection.createStatement();
             statement2 = connection.createStatement();
             statement3 = connection.createStatement();
             statement4 = connection.createStatement();
             resultset1 = statement1.executeQuery(" SELECT  *  FROM  " + TABLE_SELL + " LIMIT 5");
             getRecents(resultset1, statement1, statement2, statement3, recents);
       } catch(Exception e) {
          e.printStackTrace();
       }
       return recents;
   }

   public void searchByName(Statement statement1, Statement statement2, List<Book> searched, String bookName) throws SQLException {
               ResultSet rs1 = statement1.executeQuery(" SELECT  *  FROM  " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_NAME + "=" + "'" + bookName.toLowerCase() + "'");
               int id = 0;
               String name = "";
               float price = 0;
               String condition = "";
               if(rs1.next()) {
                   id = rs1.getInt(COLUMN_BOOK_CATEGORY_ID);
                    name = rs1.getString(COLUMN_BOOK_NAME);
                    price = rs1.getFloat(COLUMN_BOOK_PRICE);
                    condition = rs1.getString(COLUMN_BOOK_CONDITION);
               }
               ResultSet rs2 = statement2.executeQuery(" SELECT " + COLUMN_CATEGORY_NAME + " FROM " + TABLE_CATEGORIES + " WHERE " + COLUMN_CATEGORY_ID + "=" + "'" + id + "'");
               if(rs2.next()) {
                   String category = rs2.getString(COLUMN_CATEGORY_NAME);                   
                   searched.add(new Book(category, name, price, condition));
               }                               
   }

   public void searchByCategory(Statement statement1, Statement statement2, List<Book> searched, String categoryName) throws SQLException{
               ResultSet rs1 = statement1.executeQuery(" SELECT " + COLUMN_CATEGORY_ID + " FROM " + TABLE_CATEGORIES + " WHERE " + COLUMN_CATEGORY_NAME + "=" + "'" + categoryName + "'");
               int id = 0;
               if(rs1.next()) {
                   id = rs1.getInt(COLUMN_CATEGORY_ID);
               }
               ResultSet rs2 = statement2.executeQuery(" SELECT  *  FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_CATEGORY_ID + "=" + "'" + id + "'");
               while(rs2.next()) {
                   String name = rs2.getString(COLUMN_BOOK_NAME);
                   float price = rs2.getFloat(COLUMN_BOOK_PRICE);
                   String condition = rs2.getString(COLUMN_BOOK_CONDITION);
                   searched.add(new Book(categoryName, name, price, condition));
               }               
       
   }
   
   public List<Book> getSearchedCategory(String categoryName, String bookName) {
       List<Book> searched = new ArrayList<>();
       try {
           open();
           Statement statement1 = connection.createStatement();
           Statement statement2 = connection.createStatement();
           
           if(bookName != null) {
              searchByName(statement1, statement2, searched, bookName);
           } else {
               searchByCategory(statement1, statement2, searched, categoryName);
           }
            
       } catch(Exception e) {
           e.printStackTrace();
       }
       return searched;
   }
   
   public Book getBook(String bookName) {
        Book b = null;
        try {
            open();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(" SELECT  *  FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_NAME + "=" + "'" + bookName + "'");
            b = new Book(getCategory(rs.getInt(COLUMN_CATEGORY_ID)), bookName, rs.getDouble(COLUMN_BOOK_PRICE), rs.getString(COLUMN_BOOK_CONDITION));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return b;
   }
   
   public void addBook(String name, String category, String condition, double price) {
       try {
           open();
           Statement statement = connection.createStatement();
           int id = getCategoryID(category);
           Timestamp time = new Timestamp(System.currentTimeMillis());           
           statement.executeUpdate("INSERT INTO" + TABLE_BOOKS + "(" + COLUMN_CATEGORY_ID + "," + COLUMN_BOOK_NAME + "," + COLUMN_BOOK_PRICE + "," + COLUMN_BOOK_CONDITION + ") VALUES('" + id + "', '" + name + "', '" + price + "', '" + "', '" + condition + "')");
           statement.executeUpdate("INSERT INTO" + TABLE_SELL + "VALUES('" + id + "', '" + time + "'");
       } catch(Exception e) {
           
       }
   }
   
   public int getCategoryID(String category) {
       int id = 0;
       try {
           open();
           Statement statement = connection.createStatement();
           ResultSet rs = statement.executeQuery("  SELECT  " + COLUMN_CATEGORY_ID + " FROM " + TABLE_CATEGORIES + " WHERE " + COLUMN_CATEGORY_NAME + "=" + "'" + category  + "'");
           if(rs.next()) {
               id  = rs.getInt(COLUMN_CATEGORY_ID);
           }
       } catch(Exception e) {
           
       }
       return id;
   }
   public String getCategory(int id) {
       String category = null;
       try {
           Statement statement = connection.createStatement();
           ResultSet rs = statement.executeQuery("  SELECT  " + COLUMN_CATEGORY_NAME + "  FROM  " + TABLE_CATEGORIES + "  WHERE  " + COLUMN_CATEGORY_ID + " = " + "'" + id + "'");
           if(rs.next()) {
               category = rs.getString(COLUMN_CATEGORY_NAME);
           }
       } catch(Exception e) {
       }
       return category;
   }
   
   public int getBookID(String bookName) {
       int id = 0;
      try {
          open();
          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(" SELECT  " + COLUMN_BOOK_ID + "  FROM  " + TABLE_BOOKS + "  WHERE  " + COLUMN_BOOK_NAME + "=" + "'" + bookName + "'");
          if(rs.next()) {
              id = rs.getInt(COLUMN_BOOK_ID);
          }
      } catch(Exception e) {
          
      }
        return id;
   }
   
   public void saveCartData() {
       try {
           open();
           Statement statement = connection.createStatement();
           int userID = User.getUserID();
           List<Book> books = new Cart().getCartItems();
           for(Book b : books){ 
               int id = getBookID(b.getName());
               statement.executeUpdate("INSERT INTO " + TABLE_CARTS + " VALUES(" + "'" + userID + "', '" + id + "')");
           }
       } catch(Exception e) {
           
       }
   }

   public void getUUID() {

   }
}
