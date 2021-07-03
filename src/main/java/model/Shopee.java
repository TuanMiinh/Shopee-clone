/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Man Nguyen
 */
public class Shopee {

    Gson _gson = new Gson();

    public void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");

        String res = _gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

    public ArrayList getArrayJsonByKey(String json, String key) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray jsonArr = jsonObject.getAsJsonArray(key);

        return _gson.fromJson(jsonArr, ArrayList.class);
    }

    // chưa chắc chỗ này lắm
    public String toJson(Object obj) {
        return _gson.toJson(obj);
    }

    public Object fromJson(String json, Class className) {
        return _gson.fromJson(json, className);
    }

    public String toJsonUsers(List<User> objArr) {
        return _gson.toJson(objArr);
    }

    public String toJsonProducts(List<Product> objArr) {
        return _gson.toJson(objArr);
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }
}
