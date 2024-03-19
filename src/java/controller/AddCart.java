package controller;

import dal.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import model.Cart;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name="AddCart", urlPatterns={"/addcart"})
public class AddCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        CartDAO cdao = new CartDAO();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String item_id_raw = request.getParameter("item_id");
//        String quantity_raw = request.getParameter("quantity");
        try {
            int item_id = Integer.parseInt(item_id_raw);
            Cart c = new Cart();
            c.setUserName(username);
            c.setItemId(item_id);
            c.setQuantity(1);
            cdao.addCart(c);
            session.setAttribute("mes", "Succesfully add the item");
            response.sendRedirect("display");
        } catch (NumberFormatException e) {
            System.out.println("hehehe");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
}
