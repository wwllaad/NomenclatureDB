import java.io.IOException;


@javax.servlet.annotation.WebServlet(name = "Main", urlPatterns = "/main")
public class Main extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/main.jsp").forward(request,response);
    }
}
