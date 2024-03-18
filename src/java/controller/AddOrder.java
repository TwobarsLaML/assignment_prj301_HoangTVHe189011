package controller;

import dal.CartDAO;
import dal.ItemDAO;
import dal.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Order;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name="AddOrder", urlPatterns={"/addorder"})
public class AddOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String[] id_raw = request.getParameterValues("chosen");
        if (id_raw == null) {
            request.setAttribute("mes", "You haven't chosen any item");
            request.getRequestDispatcher("cart").forward(request, response);
        }
        ItemDAO idao = new ItemDAO();
        CartDAO cdao = new CartDAO();
        List<Cart> cList_all = cdao.getCartsFromID(acc.getUsername());
        List<Order> oList = new ArrayList<>();
        OrderDAO odao = new OrderDAO();
        for(String id_str : id_raw) {
            try {
                int id = Integer.parseInt(id_str);
                for(Cart c : cList_all) {
                    c.setItem(idao.getItemById(c.getItemId()));
                    if(c.getId()==id) {
                        Order o = new Order();
                        o.setUsername(c.getUserName());
                        o.setItemid(c.getItemId());
                        o.setQuantity(c.getQuantity());
                        o.setTotalPrice(c.getItem().getPrice()*c.getQuantity());
                        oList.add(o);
                        odao.AddOrders(o);
                        cdao.deleteCartByID(c.getId());
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        request.setAttribute("mes", "Done ordered.");
        request.getRequestDispatcher("cart").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
}
