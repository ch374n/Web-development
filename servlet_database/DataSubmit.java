import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/DataSubmit"})
public class DataSubmit extends HttpServlet {

    private String DRIVER = "com.mysql.jdbc.Driver";
    private String DB_NAME = "college_2";
    private String USERNAME = "root";
    private String PASSWORD = "root";
    private String CONNECTION = "jdbc:mysql://localhost:3306/" + DB_NAME;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1> STUDENTS INFORMATION </h1>");
            
            String studentName = request.getParameter("student_name");
            int mobileNumber = Integer.parseInt(request.getParameter("mobile_number"));
            String emailID = request.getParameter("email_id");
            Date date = Date.valueOf(request.getParameter("birth_date"));
            
            try {
                   Class.forName(DRIVER);  
                   Connection connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
                   Statement statement1 = connection.createStatement();
                   Statement statement2 = connection.createStatement();
                   

                   statement2.executeUpdate("INSERT INTO students VALUES('" + 
                           studentName + "', '" 
                           + mobileNumber + "', '" 
                           + emailID + "', '" 
                           + date + "')");
                   
                   ResultSet rs = statement1.executeQuery("SELECT * FROM students");
                   
                   out.println("<table>");
                   out.println("<tr>"
                           + "<th>Name</th> "
                           + "<th>Mobile No.</th>"
                           + "<th>Email</th>"
                           + "<th>Birthdate</th>"
                           + " </tr>");
                   
                   while (rs.next()) {
                       out.println("<tr>"
                               + "<td>" + rs.getString("name") + "</td>" 
                               + "<td>"  + rs.getInt("mobile_no") + "</td>" 
                               + "<td>" + rs.getString("email") + "</td>" 
                               + "<td>" + rs.getDate("birth_date")
                               + "</tr>");
                   }
                   
                   out.println("</table>");
                   out.println("<button><a href =\"index.jsp\">GO BACK</a></button>");
                   
               } catch(Exception e) {
                   out.println(e);
               }

            } catch(Exception e) {
                
            } 
        }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
