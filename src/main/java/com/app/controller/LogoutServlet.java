package com.app.controller;
import com.app.utils.Modules;
import com.app.utils.SessionUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //Supprimer la session
        SessionUtil.destroySession(request);

        //Supprimer les cookies
        Modules.removeUserCookies(request, response);

        // Redirect to the login page
        response.sendRedirect("login.jsp");
    }
}