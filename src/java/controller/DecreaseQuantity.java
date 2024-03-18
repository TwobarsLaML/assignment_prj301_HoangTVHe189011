package controller;

import dal.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Cart;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name = "DecreaseQuantity", urlPatterns = {"/decrease"})
public class DecreaseQuantity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDAO cdao = new CartDAO();
        Account account = (Account) session.getAttribute("account");
        String cart_id = request.getParameter("cart_id");
        try {
            int id = Integer.parseInt(cart_id);
            List<Cart> list = cdao.getCartsFromID(account.getUsername());
            for (Cart c : list) {
                if (c.getId() == id) {
                    if (c.getQuantity() > 1) {
                        c.setQuantity(c.getQuantity() - 1);
                        cdao.changeCartInfo(c);
                    }
                    request.getRequestDispatcher("cart").forward(request, response);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
