
package com.example.MusicStore;

import DAO.AlbumDAO;
import DAO.AuthorDAO;
import DAO.CompositionDAO;
import Model.Album;
import Model.Author;
import Model.Composition;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

    @WebServlet(urlPatterns = {"/composition-servlet","/insertcomposition","/updatecomposition","/compositionaddform","/deletecomposition"})
    public class CompositionServlet extends HttpServlet {
        private String message;

        public void init() {

        }
        AlbumDAO al = new AlbumDAO();
        AuthorDAO au = new AuthorDAO();
        CompositionDAO co = new CompositionDAO();

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String action = request.getServletPath();


            switch (action) {
                case "/compositionaddform": {

                    Composition composition = null;
                    if (request.getParameter("id") != null) {
                        composition = co.selectCompositionById(Integer.parseInt(request.getParameter("id")));
                    }
                    request.setAttribute("albums",al.getAlbums());
                    request.setAttribute("composition", composition);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/compositionform.jsp");
                    dispatcher.forward(request, response);

                }
                break;
                case "/deletecomposition": {

                    co.deleteCompositionbyId(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("composition-servlet");
                }
                break;
                case "/insertcomposition": {

                    String compositionName = request.getParameter("CompositionName");
                    int albumId = Integer.parseInt(request.getParameter("AlbumId"));
                    int duration = Integer.parseInt(request.getParameter("Duration"));
                    Album album = al.selectAlbumById(albumId);
                    Composition composition = new Composition(duration,compositionName,album);
                    try {
                        co.createComposition(composition);
                        response.sendRedirect("composition-servlet");
                    }catch (Exception e )
                    {
                        request.setAttribute("inserterror", e.getMessage());
                        request.setAttribute("composition", composition);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/compositionform.jsp");
                        dispatcher.forward(request, response);
                        e.printStackTrace();
                    }
                }
                break;
                case "/updatecomposition": {

                    String compositionName = request.getParameter("CompositionName");
                    int albumId = Integer.parseInt(request.getParameter("AlbumId"));
                    int duration = Integer.parseInt(request.getParameter("Duration"));
                    Album album = al.selectAlbumById(albumId);


                    try {
                        co.updateComposition(albumId,compositionName,duration,Integer.parseInt(request.getParameter("id")));
                        response.sendRedirect("composition-servlet");
                    }
                    catch (Exception e)
                    {
                        request.setAttribute("inserterror", e.getMessage());
                        request.setAttribute("composition", new Composition(duration,compositionName));
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/compositionform.jsp");
                        dispatcher.forward(request, response);
                        e.printStackTrace();
                    }
                }
                break;
                default: {
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/composition-jsp.jsp");
                    request.setAttribute("compositions", co.getCompositions());

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
