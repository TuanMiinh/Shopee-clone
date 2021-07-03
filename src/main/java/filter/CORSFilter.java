package filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Shopee;
import model.User;



import service.TokenUtil;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Man Nguyen
 */
@WebFilter(urlPatterns = "/*")
public class CORSFilter implements Filter {

    /**
     * Default constructor.
     */
    TokenUtil tokenUtil = new TokenUtil();


    Shopee shopee = new Shopee();
    private ServletContext context;

    public CORSFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");


        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        chain.doFilter(request, servletResponse);


//        String URL = request.getRequestURL().toString();
//
//        if (URL.contains("admin") || URL.contains("test")) {
//            final String authorization = request.getHeader("Authorization");
//            if (authorization != null)
//                chain.doFilter(request, servletResponse);
//
//            else shopee.sendAsJson(response, "Access Denied");
//        } else shopee.sendAsJson(response, "Access Denied");


        //Phan ni tui tinh' lam checkToken 2 lan de de phan quyen voi bao mat
//        if (URL.contains("admin") || URL.contains("test")) {
//            System.out.println(((User) sessionUtil.getValue(request, "USER")).getUsername());
//
//
//            final String authorization = request.getHeader("Authorization");
//            String username = null;
//            String jwt = authorization;
//
//            if (authorization != null) {
//                jwt = authorization;
//                username = tokenUtil.extractUsername(jwt);
//                if (username != null && tokenUtil.validateToken(jwt, (User) sessionUtil.getValue(request, "USER"))) {
//                    chain.doFilter(servletRequest, servletResponse);
//                } else shopee.sendAsJson(response, "Access Denied");
//            } else shopee.sendAsJson(response, "Access Denied");
//
//        } else chain.doFilter(request, servletResponse);
        // pass the request along the filter chain

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
    }

}
