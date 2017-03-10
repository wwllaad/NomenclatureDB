import com.tavrida.bve.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "DeletePage", urlPatterns = "/deletePage")
public class DeletePage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> products = DAO.getAllProducts();

        if (products==null){
         String msg = "Nothing to delete. DB is empty";
         request.setAttribute("msg", msg);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("WEB-INF/delete.jsp").forward(request,response);
    }
}
