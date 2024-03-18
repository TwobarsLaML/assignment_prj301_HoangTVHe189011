package controller;

import dal.ItemDAO;
import dal.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Order;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name="Order", urlPatterns={"/order"})
public class DisplayOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        try {
            OrderDAO odao = new OrderDAO();
            ItemDAO idao = new ItemDAO();
            List<Order> list = odao.getOrdersByUser(acc.getUsername());
            for(Order o:list) {
                o.setItem(idao.getItemById(o.getItemid()));
            }
            request.setAttribute("oList", list);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println(e);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}
