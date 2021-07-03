/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.purchase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.Order;
import model.OrderInformation;
import model.Shopee;
import model.dao.ItemDao;
import model.dao.OrderDao;
import service.GenerateRandomId;

/**
 * @author Man Nguyen
 */
@WebServlet("/admin-order")
public class Purchase extends HttpServlet {

    Shopee shopee = new Shopee();
    OrderDao orderDao = new OrderDao();
    ItemDao itemDao = new ItemDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Purchase</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Purchase at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        if (id != null) {
            Order order = orderDao.getOrderByID(id);
            ArrayList<Item> itemList = itemDao.getItemByOrderId(id);
            OrderInformation orderInfo = new OrderInformation(itemList, order.getNote(), order.getAddress(), order.getUserId());
//        String orderInfoJson = shopee.toJson(orderInfo);
            shopee.sendAsJson(response, orderInfo);
        } else {
            ArrayList<Order> orders = orderDao.getOrderByUserId(userId);
            HashMap<String, OrderInformation> orderInfo = new HashMap<>();
            for (Order order : orders) {
                ArrayList<Item> items = itemDao.getItemByOrderId(order.getId());
                orderInfo.put(order.getId(), new OrderInformation(items, order.getNote(), order.getAddress(), order.getUserId()));
            }
            shopee.sendAsJson(response, orderInfo);
        }


    }
//{
//    "items": [
//        {
//            "productId": "60d30a7cfc13ae1c6700012e",
//            "quantity": 72
//        },
//        {
//            "productId": "60d30a7cfc13ae1c67000131",
//            "quantity": 51
//        },
//        {
//            "productId": "60d30a7cfc13ae1c67000134",
//            "quantity": 17
//        },
//        {
//            "productId": "60d30a7cfc13ae1c67000137",
//            "quantity": 81
//        }
//    ],
//    "note": "zero tolerance",
//    "address": "9791 Acker Park",
//    "userId": "302a1dc2-4388-447a-b86c-d5c0d044afc4"
//}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemDao itemDao = new ItemDao();
        OrderDao orderDao = new OrderDao();

        String orderInforJson = shopee.getBody(request);
        OrderInformation orderInfor = (OrderInformation) shopee.fromJson(orderInforJson, OrderInformation.class);
        ArrayList<Item> listItems = orderInfor.getItems();

        String orderId = GenerateRandomId.next();

        orderDao.addOrder(orderId, orderInfor.getNote(), orderInfor.getAddress(), orderInfor.getUserId());

        for (Item item : listItems) {
            try {
                itemDao.addItem(item, orderId);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        shopee.sendAsJson(response, orderInfor);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        String orderInfoJson = shopee.getBody(req);
        OrderInformation orderInfo = (OrderInformation) shopee.fromJson(orderInfoJson, OrderInformation.class);
        orderDao.updateOrder(new Order(orderId, orderInfo.getNote(), orderInfo.getAddress(), orderInfo.getUserId()));
        itemDao.deleteItemByOrderId(orderId);

        ArrayList<Item> items = orderInfo.getItems();
        for (Item item : items) {
            try {
                itemDao.addItem(item,orderId);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        shopee.sendAsJson(resp,orderInfo);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        orderDao.deleteOrder(orderId);
        itemDao.deleteItemByOrderId(orderId);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
