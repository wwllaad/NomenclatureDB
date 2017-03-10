import com.tavrida.bve.DAO;
import com.tavrida.bve.Nomenclature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAll", urlPatterns = "/showall")
public class ShowAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Nomenclature> tempData = DAO.getALL();
        request.setAttribute("data", tempData);
        request.getRequestDispatcher("WEB-INF/showAll.jsp").forward(request,response);

    }
}
