package com.example.MusicStore;


import DAO.AlbumDAO;
import DAO.AuthorDAO;
import DAO.CompositionDAO;
import Model.Album;
import Model.Author;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/album-servlet","/insertalbum","/updatealbum","/albumaddform","/deletealbum","/ajaxhandler"})
public class AlbumServlet extends HttpServlet {
    private String message;

    public void init() {

    }
    AlbumDAO al = new AlbumDAO();
    AuthorDAO au = new AuthorDAO();
    CompositionDAO co = new CompositionDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String action = request.getServletPath();


        switch (action) {
            case "/ajaxhandler":
                {
                    String input = request.getParameter("input");
                    String names = authorsByNameString(input);
                    OutputStream outStream = response.getOutputStream();
                    outStream.write(names.getBytes("UTF-8"));
                    outStream.flush();
                    outStream.close();

                }
                break;
            case "/albumaddform": {

                Album album = null;
                if (request.getParameter("id") != null) {
                    album = al.selectAlbumById(Integer.parseInt(request.getParameter("id")));
                }
                request.setAttribute("authorlist",au.getAuthors());
                request.setAttribute("album", album);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/albumform.jsp");
                dispatcher.forward(request, response);

            }
            break;
            case "/deletealbum": {

                al.deleteAlbumbyId(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("album-servlet");
            }
            break;
            case "/insertalbum": {

                String authorName = request.getParameter("AuthorName");
                Author author = au.getAuthorByName(authorName);
                if(author == null)
                {
                    try {
                        author = new Author(authorName);
                        au.createAuthor(author);
                    }
                    catch (Exception e){}
                }
                Album album = new Album(request.getParameter("Genre"), request.getParameter("AlbumName"), author);
                try {
                    al.createAlbum(album);
                    response.sendRedirect("album-servlet");
                }catch (Exception e )
                {
                    request.setAttribute("inserterror", e.getMessage());
                    request.setAttribute("album", album);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/albumform.jsp");
                    dispatcher.forward(request, response);
                    e.printStackTrace();
                }
            }
            break;
            case "/updatealbum": {

                String authorName = request.getParameter("AuthorName");
                Author author = au.getAuthorByName(authorName);
                if(author==null)
                {
                    author = new Author(authorName);
                    try {
                        au.createAuthor(author);
                    }
                    catch (Exception e){}
                }
                int authorID = author.getId();

                try {
                    al.updateAlbum(Integer.parseInt(request.getParameter("id")), authorID,
                            request.getParameter("AlbumName"), request.getParameter("Genre"));
                    response.sendRedirect("album-servlet");
                }
                catch (Exception e)
                {
                    request.setAttribute("inserterror", e.getMessage());
                    request.setAttribute("album", new Album(request.getParameter("Genre"),request.getParameter("AlbumName"),
                            author));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/albumform.jsp");
                    dispatcher.forward(request, response);
                    e.printStackTrace();
                }
            }
            break;
            default: {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/album-jsp.jsp");
                request.setAttribute("albums", al.getAlbums());

                dispatcher.forward(request, response);

            }
            break;

        }


    }

    public String authorsByNameString(String name)
    {
        List<Author> singres = au.getAuthorsLike(name);
        List<String> singerNames = au.getAuthors().stream().map(singer -> singer.getName()).collect(Collectors.toList());
        ObjectMapper obj = new ObjectMapper();
        String names = "";
        try {
            names = obj.writeValueAsString(singerNames);
        }
        catch (JsonProcessingException e) { e.printStackTrace(); }
        return names;
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