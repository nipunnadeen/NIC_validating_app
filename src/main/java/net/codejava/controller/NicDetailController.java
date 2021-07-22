package net.codejava.controller;

import net.codejava.model.UserDetailModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/nicDetails")
public class NicDetailController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetailModel userDetailModel = new UserDetailModel();
        String nic = request.getParameter("nic");
        if(!userDetailModel.validateNIC(nic)) {
            request.setAttribute("NICFindFailResponse", "Please enter correct NIC number");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            ArrayList<String> nicData = userDetailModel.getNICDetails(nic);
            request.setAttribute("birthDay", nicData.get(0));
            request.setAttribute("gender", nicData.get(1));
            request.setAttribute("age", nicData.get(2));

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
