/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

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
import model.Product;
import model.Shopee;
import model.dao.ProductDao;
import model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/api-products")
public class Products extends HttpServlet {

    Shopee shopee = new Shopee();
    Logger logger = LogManager.getLogger(FirstServlet.class.getName());
    ProductDao productDao = new ProductDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.addHeader("Access-Control-Allow-Origin", "*");

//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//        response.addHeader("Access-Control-Max-Age", "1728000");
//        
        List<Product> products = new ArrayList<>();
        String id = request.getParameter("id");
        String page = request.getParameter("page");

        if (id != null) {
            Product product = new Product();
            product = productDao.getProductById(id);
            String result = shopee.toJson(product);
            Product productJson = (Product) shopee.fromJson(result, Product.class);

            shopee.sendAsJson(response, productJson);
        } else if (page != null) {
            products = productDao.getProductsByPage(page);
            String result = shopee.toJsonProducts(products);
            Product[] productsJson = (Product[]) shopee.fromJson(result, Product[].class);

            shopee.sendAsJson(response, productsJson);
        } else {
            products = productDao.getProducts();
            String result = shopee.toJsonProducts(products);
            Product[] productsJson = (Product[]) shopee.fromJson(result, Product[].class);

            shopee.sendAsJson(response, productsJson);
        }
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = shopee.getBody(request);
        Product product = (Product) shopee.fromJson(json, Product.class);
        productDao.createProducts(product);
        shopee.sendAsJson(response, product);
    }




    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = shopee.getBody(req);
        Product product = (Product) shopee.fromJson(json,Product.class);
        productDao.updateProduct(product);
        shopee.sendAsJson(resp,product);
    }


    // gui api xoa theo id dang "id":["id1" , "id2" , "id3"]
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = shopee.getBody(req);
        ArrayList<String> arrayID = shopee.getArrayJsonByKey(json,"id");
        for (String ID : arrayID)
            productDao.deleteProduct(ID);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type: application/json");
    }
}
