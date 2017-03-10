import com.tavrida.bve.DAO;
import com.tavrida.bve.Nomenclature;
import com.tavrida.bve.QueryCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Search", urlPatterns = "/searchid")
public class Search extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Nomenclature nom = new Nomenclature();
        List<Nomenclature> tempData = new ArrayList<>();
        //Получение данных из строки поиска, метод trim убирает пробелы с обоих сторон
        String searchQuery = request.getParameter("searchid").trim();

        //условие пустой строки
        if (searchQuery.equals("")){
            nom.setPartCode("Enter ID or PartCode.");
            tempData.add(nom);
            request.setAttribute("data", tempData);
            request.getRequestDispatcher("WEB-INF/searchResults.jsp").forward(request, response);
        }

        //условие ввода числа (ID или PartCode не могут быть численными - формат Nomenclature)
        if(QueryCheck.isInt(searchQuery)) {
            nom.setPartCode("Invalid ID or PartCode. Enter correct ID or PartCode.");
            tempData.add(nom);
        }


        if(QueryCheck.isID(searchQuery)){
            tempData = DAO.searchById(searchQuery);
            if(tempData.isEmpty()){
                nom.setPartCode("No results found. Enter new ID or PartCode.");
                tempData.add(nom);
            }
        } else if(QueryCheck.isPartCode(searchQuery)){
            tempData = DAO.searchByPartCode(searchQuery);

            if(tempData.isEmpty()){
                nom.setPartCode("No results found. Enter new ID or PartCode.");
                tempData.add(nom);
            }
        } else {
            nom.setPartCode("Invalid ID or PartCode. Enter correct ID or PartCode.");
            tempData.add(nom);
        }
            request.setAttribute("data", tempData);
            request.getRequestDispatcher("WEB-INF/searchResults.jsp").forward(request, response);
    }


}

