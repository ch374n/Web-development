
import com.nimbalkar.chetan.HashClass;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nimbalkar.chetan.DataController;
import javax.servlet.RequestDispatcher;
import com.nimbalkar.chetan.SignIn;

@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
              String uName = request.getParameter("username");
              String passwd = request.getParameter("password");
              passwd = HashClass.digest(passwd);
              if(new DataController().validate(uName, passwd)) {
                  SignIn.setSignIn(true);
              } else {
              }
                  response.sendRedirect("index.jsp");
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

}
