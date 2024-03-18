package controller;

import dal.ItemDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Item;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name="DisplayItems", urlPatterns={"/display"})
public class DisplayItems extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String message = request.getParameter("mes");
        ItemDAO idao = new ItemDAO();
        List<Item> list = idao.getAll();
        if(message != null) {
            request.setAttribute("mes", message);
        }
        request.setAttribute("iList", list);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
}
