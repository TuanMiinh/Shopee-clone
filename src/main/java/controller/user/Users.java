/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import controller.FirstServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Shopee;
import model.User;
import model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Man Nguyen
 */
@WebServlet("/admin-users")
public class Users extends HttpServlet {

    Shopee shopee = new Shopee();
    Logger logger = LogManager.getLogger(FirstServlet.class.getName());
    UserDao userDao = new UserDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsersController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        users = userDao.getUsers();
        String result = shopee.toJsonUsers(users);
//        User newUser = (User) shopee.fromJson(result, User.class);
        User[] usersJson = (User[]) shopee.fromJson(result, User[].class);

        shopee.sendAsJson(response, usersJson);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = shopee.getBody(request);
        User user = (User) shopee.fromJson(json, User.class);
        userDao.createUser(user);
        shopee.sendAsJson(response, user);

    }
    // gui api xoa theo id dang "id":["id1" , "id2" , "id3"]
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = shopee.getBody(req);

        ArrayList<String> arrayID = shopee.getArrayJsonByKey(json, "id");
        for (String id : arrayID)
            userDao.deleteUser(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = shopee.getBody(req);
        User user = (User) shopee.fromJson(json, User.class);
        userDao.updateUser(user);
        shopee.sendAsJson(resp, user);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
