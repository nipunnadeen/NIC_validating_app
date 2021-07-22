package net.codejava.controller;

import net.codejava.model.UserDetailModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/userDetails")
public class UserRegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fName");
        String address = request.getParameter("address");
        String nationality = request.getParameter("nationality");
        String nic = request.getParameter("nic");
        String userid = request.getParameter("id");

        UserDetailModel userDetailModel = new UserDetailModel();
        if (!userDetailModel.validateNIC(nic)) {
            request.setAttribute("userFailResponse", "Please enter correct NIC number");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            try {
                if(userid == null || userid.isEmpty()) {
                    int responseData = userDetailModel.sendUserDetails(fullName, address, nationality,
                            nic);
                    if (responseData == 1) {
                        request.setAttribute("userSuccessResponse",
                                "Details saved successfully");
                    } else {
                        request.setAttribute("userFailResponse",
                                "Details didn't save. Please try again");
                    }
                } else {
                    int responseData = userDetailModel.updateUserDetails(Integer.parseInt(userid),
                            fullName, address, nationality, nic);
                    if (responseData == 1) {
                        request.setAttribute("userSuccessResponse",
                                "Details update successfully");
                    } else {
                        request.setAttribute("userFailResponse",
                                "Details didn't update. Please try again");
                    }
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetailModel userDetailModel = new UserDetailModel();
        String nic = request.getParameter("nic");
        if (!userDetailModel.validateNIC(nic)) {
            request.setAttribute("NICFailResponse", "Please enter correct NIC number");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            try {
                ArrayList<String> userData = userDetailModel.getUserData(nic);
                if (userData.size() != 0) {
                    request.setAttribute("id", userData.get(0));
                    request.setAttribute("fullname", userData.get(1));
                    request.setAttribute("address", userData.get(2));
                    request.setAttribute("nationality", userData.get(3));
                    request.setAttribute("nicnumber", userData.get(4));
                } else {
                    request.setAttribute("NICFailResponse", "User is not found");
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
