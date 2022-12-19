package com.example.MusicStore;


import DAO.*;
import Model.*;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/compositionlist-servlet","/compshandler"})
public class CompositionListServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World1!";
    }

    CompositionDAO co = new CompositionDAO();
    AlbumDAO al = new AlbumDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String action = request.getServletPath();


        switch (action) {
            case "/compshandler": {
                String input = request.getParameter("input");
                String names = albumsByNameString(input);
                OutputStream outStream = response.getOutputStream();
                outStream.write(names.getBytes("UTF-8"));
                outStream.flush();
                outStream.close();

            }
            break;
            default:
            String albumName = request.getParameter("AlbumName");
            if (albumName == null) {
                albumName = "";
            }

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/compositionlist-jsp.jsp");

            request.setAttribute("albumname", albumName);
            request.setAttribute("compositions", co.getCompositionsByAlbum(albumName));
            dispatcher.forward(request, response);
        }

    }

    private String albumsByNameString(String name) {
        List<Album> albums = al.getAlbumsLike(name);
        List<String> albumNames = albums.stream().map(singer -> singer.getAlbumName()).collect(Collectors.toList());
        ObjectMapper obj = new ObjectMapper();
        String names = "";
        try {
            names = obj.writeValueAsString(albumNames);
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