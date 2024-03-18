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
import model.Account;
import model.Item;
import model.Order;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name = "CreateOrder", urlPatterns = {"/buynow"})
public class CreateOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String item_id_raw = request.getParameter("itemid");
        ItemDAO idao = new ItemDAO();
        try {
            int item_id = Integer.parseInt(item_id_raw);
            Item i = idao.getItemById(item_id);
            request.setAttribute("item", i);
            request.getRequestDispatcher("buynow.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String quantity_raw = request.getParameter("quantity");
        String itemid_raw = request.getParameter("itemid");
        ItemDAO idao = new ItemDAO();
        try {
            int quantity = Integer.parseInt(quantity_raw);
            int itemid = Integer.parseInt(itemid_raw);
            Item i = idao.getItemById(itemid);
            Order o = new Order();
            o.setUsername(a.getUsername());
            o.setItemid(itemid);
            o.setQuantity(quantity);
            o.setTotalPrice(quantity * i.getPrice());
            OrderDAO odao = new OrderDAO();
            odao.AddOrders(o);
            request.setAttribute("mess", "Successully ordered");
            doGet(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
