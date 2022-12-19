package com.example.MusicStore;

import DAO.AlbumDAO;
import DAO.AuthorDAO;
import Model.Album;
import Model.Author;
import Model.Composition;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

       Author author1 = new Author("Author1");
       List<Album> albums1 = new LinkedList<>();

       Album al1 = new Album("retrowave","Author1 Album");
       al1.setAuthor(author1);
        Composition comp1 = new Composition(12,"Author1 comp");
        comp1.setAlbum(al1);
        List<Composition> comps = new LinkedList<Composition>();
        comps.add(comp1);
        al1.setCompositions(comps);
        albums1.add(al1);
        author1.setAlbums(albums1);
        AuthorDAO authorDao = new AuthorDAO();
        try {
            authorDao.createAuthor(author1);
        }
        catch (Exception e )
        {

        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}