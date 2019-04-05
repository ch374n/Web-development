<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! 
               String DRIVER = "com.mysql.jdbc.Driver";
               String DB_NAME = "college";
               String USERNAME = "root";
               String PASSWORD = "root";
               String CONNECTION = "jdbc:mysql://localhost:3306/" + DB_NAME;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database application using JSP</title>
    </head>
    <body>
        <h1> STUDENT NAMES </h1>
        <% 
               String name = request.getParameter("student_name");
               
               try {
                   Class.forName(DRIVER);  
                   Connection connection= DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);  
                   Statement statement1 = connection.createStatement();
                   Statement statement2 = connection.createStatement();
                   
                   statement2.executeUpdate("INSERT INTO students(name) values('" + name + "')");    
                   
                   ResultSet rs = statement1.executeQuery("SELECT name FROM students");
                   out.println("<ol>");
                   while (rs.next()) {
                       out.println("<li>" + rs.getString("name") + "</li>");
                   }
                   out.println("</ol>");
               } catch(Exception e) {
                   out.println(e);
               }
        %>
        <button><a href="index.jsp">GO BACK</a></button>
    </body>
</html>

