import com.tavrida.bve.DAO;
import com.tavrida.bve.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "DeleteNomenclature", urlPatterns = "/deleteNomenclature")
public class DeleteNomenclature extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //получаем удаляемого ProductCode
        String productToDelete = request.getParameter("nomenclatureSelect");

        if(productToDelete == null){
            String msg = "Nothing to delete. DB is empty";
            request.setAttribute("msg", msg);
        } else {
            Database.connect();
            Database.deleteByProductCode(productToDelete);
            Database.close();

            String msg = productToDelete + " successfully removed from DB.";
            request.setAttribute("msg", msg);
            //получаем все ProductCode для вывода в список выбора ProductCode на удаление
            List<String> products = DAO.getAllProducts();
            request.setAttribute("products", products);
        }
        request.getRequestDispatcher("WEB-INF/delete.jsp").forward(request,response);
    }
}
