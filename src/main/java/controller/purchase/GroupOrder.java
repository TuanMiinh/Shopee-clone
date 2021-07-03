package controller.purchase;

import model.*;
import model.dao.ItemDao;
import model.dao.OrderDao;
import model.dao.OrderGroupDAO;
import model.dao.UserDao;
import service.GenerateRandomId;
import service.TokenUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@WebServlet(name = "GroupOrder", value = "/GroupOrder")
public class GroupOrder extends HttpServlet {

    Shopee shopee = new Shopee();
    TokenUtil tokenUtil = new TokenUtil();
    OrderGroupDAO orderGroupDAO = new OrderGroupDAO();
    UserDao userDao = new UserDao();
    OrderDao orderDao = new OrderDao();
    ItemDao itemDao = new ItemDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderCode = GenerateRandomId.next();
        String orderSample = "SAMPLE";
        String username = tokenUtil.extractUsername(request.getHeader("Authorization"));
        User user = userDao.getUserByUsername(username);
        orderGroupDAO.createOrderByGroup(new OrderByGroup(orderCode, orderSample));
        shopee.sendAsJson(response, orderCode);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("orderID") != null) {
            String orderCode = request.getParameter("orderID");
            if (orderGroupDAO.getOrderGroupByOrderCode(orderCode) != null)
                shopee.sendAsJson(response, "ACCESS");
            else shopee.sendAsJson(response, "DENIED");
        } else if (request.getHeader("orderCode") != null) {
            orderGroupDAO.deleteOrderGroupByOrderID("SAMPLE");
            String orderCode = request.getHeader("orderCode");
            String orderInforJson = shopee.getBody(request);
            OrderInformation orderInfor = (OrderInformation) shopee.fromJson(orderInforJson, OrderInformation.class);
            ArrayList<Item> listItems = orderInfor.getItems();

            String orderId = GenerateRandomId.next();

            String username = tokenUtil.extractUsername(request.getHeader("Authorization"));
            User user = userDao.getUserByUsername(username);
            orderGroupDAO.createOrderByGroup(new OrderByGroup(orderCode, orderId));

            orderDao.addOrder(orderId, orderInfor.getNote(), orderInfor.getAddress(), user.getId());

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


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderCode = req.getHeader("orderCode");
        String address = req.getParameter("address");
        List<OrderInformation> orderInfo = orderGroupDAO.getOrderInfoByCode(orderCode);
        List<OrderByGroup> orderByGroup = orderGroupDAO.getOrderGroupByOrderCode(orderCode);
        for (OrderByGroup orderGroup : orderByGroup) {
            orderGroup.setAddress(address);
            orderGroupDAO.updateOrder(orderGroup);
            Order order = orderDao.getOrderByID(orderGroup.getOrderID());
            order.setAddress(address);
            orderDao.updateOrder(order);
        }
        shopee.sendAsJson(resp, new OrderByGroupInfo(orderCode, orderInfo, address));

    }
}
