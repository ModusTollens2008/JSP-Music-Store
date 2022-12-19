package com.example.MusicStore;


import DAO.*;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/author-servlet", "/deleteauthor", "/insertauthor", "/authoraddform", "/updateauthor"})
public class AuthorServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World1!";
    }

    AuthorDAO us = new AuthorDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/authoraddform": {

                Author user = null;
                if (request.getParameter("id") != null) {
                    user = us.selectAuthorById(Integer.parseInt(request.getParameter("id")));
                }

                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/authorform.jsp");
                dispatcher.forward(request, response);

            }
            break;
            case "/deleteauthor": {
                us.deleteAuthorbyId(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("author-servlet");
            }
            break;
            case "/insertauthor": {



                Author user = new Author(request.getParameter("UserName"));
                try {
                    System.out.println(request.getParameter("first_name"));
                    us.createAuthor(user);

                    response.sendRedirect("author-servlet");
                } catch (Exception e) {
                    request.setAttribute("inserterror", e.getMessage());
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/authorform.jsp");
                    dispatcher.forward(request, response);
                    e.printStackTrace();
                }
            }
            break;
            case "/updateauthor": {
                try {
                    System.out.println(request.getParameter("UserName"));
                    System.out.println(request.getParameter("id"));
                    us.updateAuthor(Integer.parseInt(request.getParameter("id")),
                            request.getParameter("UserName"));
                    response.sendRedirect("author-servlet");
                } catch (Exception e) {
                    Author user = new Author(request.getParameter("AuthorName"));
                    user.setId(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("updateerror", e.getMessage());
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/authorform.jsp");
                    dispatcher.forward(request, response);
                    e.printStackTrace();
                }

            }
            break;
            default: {

                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/author-jsp.jsp");
                request.setAttribute("users", us.getAuthors());
                dispatcher.forward(request, response);
            }
            break;

        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    public void destroy() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }
}