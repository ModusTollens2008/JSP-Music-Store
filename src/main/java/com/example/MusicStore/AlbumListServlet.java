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

@WebServlet(urlPatterns = {"/albumlist-servlet"})
public class AlbumListServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World1!";
    }

    AlbumDAO al = new AlbumDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String authorName = request.getParameter("AuthorName");
        if(authorName==null)
        {
            authorName="";
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/albumlist-jsp.jsp");

        request.setAttribute("authorname",authorName);
        request.setAttribute("albums", al.getAlbumsByAuthor(authorName));
        dispatcher.forward(request, response);

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