/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.purchase.Purchase;
import model.*;
import model.dao.ItemDao;
import model.dao.OrderDao;
import model.dao.OrderGroupDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import model.dao.UserDao;
import service.GenerateRandomId;

import service.TokenUtil;


/**
 * @author Man Nguyen
 */

@WebServlet(urlPatterns = "/test")
public class FirstServlet extends HttpServlet {

    Shopee shopee = new Shopee();
    Logger logger = LogManager.getLogger(FirstServlet.class.getName());
    UserDao userDao = new UserDao();
    OrderDao orderDao = new OrderDao();
    ItemDao itemDao = new ItemDao();
    TokenUtil tokenUtil = new TokenUtil();
    OrderGroupDAO orderGroupDAO = new OrderGroupDAO();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

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
            out.println("<title>Servlet FirstServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FirstServlet at " + request.getContextPath() + "</h1>");
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
        String orderCode = GenerateRandomId.next();
        String orderID = GenerateRandomId.next();
        //        orderDao.createOrderByGroup(new OrderByGroup(orderCode, orderID, user.getId()));
        shopee.sendAsJson(response, orderCode);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("orderID") != null) {
            String orderCode = request.getParameter("orderID");
            if (orderGroupDAO.getOrderGroupByOrderCode(orderCode) != null)
                shopee.sendAsJson(response, "ACCESS");
            else shopee.sendAsJson(response, "DENIED");
        } else if (request.getHeader("orderCode") != null) {
            String orderCode = request.getHeader("orderCode");
            String orderInforJson = shopee.getBody(request);
            OrderInformation orderInfor = (OrderInformation) shopee.fromJson(orderInforJson, OrderInformation.class);
            ArrayList<Item> listItems = orderInfor.getItems();

            String orderId = GenerateRandomId.next();

            String username = tokenUtil.extractUsername(request.getHeader("Authorization"));
            User user = userDao.getUserByUsername(username);
            orderGroupDAO.createOrderByGroup(new OrderByGroup(orderCode, orderId, user.getId()));

            orderDao.addOrder(orderId, orderInfor.getNote(), orderInfor.getAddress(), orderInfor.getUserId());

            for (Item item : listItems) {
                try {
                    itemDao.addItem(item, orderId);
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<OrderInformation> orderInfo = orderGroupDAO.getOrderInfoByCode(orderCode);

            shopee.sendAsJson(response, new OrderByGroupInfo(orderCode, orderInfo));
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
