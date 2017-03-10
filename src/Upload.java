import com.tavrida.bve.DAO;
import com.tavrida.bve.Database;
import com.tavrida.bve.Nomenclature;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Upload", urlPatterns = "/upload")
@MultipartConfig
public class Upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        final Part filePart = request.getPart("file");
        final String filePath = getFilePath(filePart);
        System.out.println(filePath);

        if(!filePath.isEmpty()) {

            Database.connect();
            Database.addExcelToDB(filePath);
            Database.close();
            String msg = "Upload Successful!";
            List<Nomenclature> tempData = DAO.fromExceltoList(filePath);
            request.setAttribute("data", tempData);
            request.setAttribute("msg", msg);
        } else {
            String msg = "Nothing to upload.";
            request.setAttribute("msg", msg);
        }

        request.getRequestDispatcher("WEB-INF/upload.jsp").forward(request,response);

    }
    //метод получения пути к файлу из пакета данных, полученных из формы Upload
    private String getFilePath(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
