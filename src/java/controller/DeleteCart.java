package controller;

import dal.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name="DeleteCart", urlPatterns={"/delete"})
public class DeleteCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        CartDAO cdao = new CartDAO();
        String cartid_raw = request.getParameter("cart_id");
        try {
            int cart_id = Integer.parseInt(cartid_raw);
            cdao.deleteCartByID(cart_id);
            request.getRequestDispatcher("cart").forward(request, response);
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
