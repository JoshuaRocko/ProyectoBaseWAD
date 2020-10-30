package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.EventoDAO;
import com.ipn.mx.modelo.dto.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion.equals("listaDeEventos")) {
            listaDeEventos(request, response);
        } else if (accion.equals("nuevo")) {
            nuevoEvento(request, response);
        } else if (accion.equals("eliminar")) {
            eliminarEvento(request, response);
        } else if (accion.equals("actualizar")) {
            actualizarEvento(request, response);
        } else if (accion.equals("guardar")) {
            almacenarEvento(request, response);
        } else if (accion.equals("mostrar")) {
            mostrarEvento(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaDeEventos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventoServlet</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">\n"
                    + "            <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n"
                    + "                <nav class=\"navbar navbar-light bg-light\">\n"
                    + "                    <a class=\"navbar-brand\" href=\"index.html\">\n"
                    + "                        <img src=\"assets/bootstrap-solid.svg\" width=\"30\" height=\"30\" class=\"d-inline-block align-top\" alt=\"\">\n"
                    + "                        Eventos\n"
                    + "                    </a>\n"
                    + "                </nav>\n"
                    + "                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                    + "                    <span class=\"navbar-toggler-icon\"></span>\n"
                    + "                </button>\n"
                    + "                <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n"
                    + "                    <ul class=\"navbar-nav\">\n"
                    + "                        <li class=\"nav-item active\">\n"
                    + "                            <a class=\"nav-link\" href=\"eventosForm.html\">Registrar Evento <span class=\"sr-only\">(current)</span></a>\n"
                    + "                        </li>\n"
                    + "                    </ul>\n"
                    + "                    <ul class=\"navbar-nav\">\n"
                    + "                        <li class=\"nav-item active\">\n"
                    + "                            <a class=\"nav-link\" href=\"EventoServlet?accion=listaDeEventos\">Lista de Eventos <span class=\"sr-only\">(current)</span></a>\n"
                    + "                        </li>\n"
                    + "                    </ul>\n"
                    + "                </div>\n"
                    + "            </nav>\n"
                    + "        </div>");
            out.println("<div class='container'>");
            out.println("<table class='table table-stripped table-responsive'>");
            out.println("<thead class='thead-dark text-center'>");
            out.println("<th>");
            out.println("Clave de Evento");
            out.println("</th>");
            out.println("<th>");
            out.println("Nombre del Evento");
            out.println("</th>");
            out.println("<th>");
            out.println("Sede");
            out.println("</th>");
            out.println("<th>");
            out.println("Fecha de Inicio");
            out.println("</th>");
            out.println("<th>");
            out.println("Fecha de Término");
            out.println("</th>");
            out.println("<th>");
            out.println("Acciones");
            out.println("</th>");
            out.println("</thead>");
            out.println("<tbody class='text-center'>");

            int idEvento;
            String nombreEvento;
            String sede;
            Date fechaInicio;
            Date fechaTermino;
            EventoDAO dao = new EventoDAO();
            try {
                List lista = dao.findAll();
                for (int i = 0; i < lista.size(); i++) {
                    Evento e = (Evento) lista.get(i);
                    idEvento = e.getIdEvento();
                    nombreEvento = e.getNombreEvento();
                    sede = e.getSede();
                    fechaInicio = e.getFechaInicio();
                    fechaTermino = e.getFechaTermino();
                    out.println("<tr>");
                    out.println("<td>" + idEvento + "</td>");
                    out.println("<td>" + nombreEvento + "</td>");
                    out.println("<td>" + sede + "</td>");
                    out.println("<td>" + fechaInicio + "</td>");
                    out.println("<td>" + fechaTermino + "</td>");
                    out.println("<td>");
                    out.println("<a href='EventoServlet?accion=eliminar&id=" + idEvento + "' class=\"btn btn-outline-danger\">Eliminar</a>");
                    out.println("<a href='EventoServlet?accion=actualizar&id=" + idEvento + "' class=\"btn btn-outline-info\">Actualizar</a>");
                    out.println("<a href='EventoServlet?accion=mostrar&id=" + idEvento + "' class=\"btn btn-outline-success\">Ver</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }
            } catch (SQLException e) {

            }
            out.println("</tbody>");

            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void nuevoEvento(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarEvento(HttpServletRequest request, HttpServletResponse response) {
        String idEvento = request.getParameter("id");
        EventoDAO eventoDAO = new EventoDAO();
        Evento evento = new Evento();
        evento.setIdEvento(Integer.valueOf(idEvento));
        try {
            eventoDAO.delete(evento);
            listaDeEventos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarEvento(HttpServletRequest request, HttpServletResponse response) {
        EventoDAO dao = new EventoDAO();
        Evento evento = new Evento();
        evento.setIdEvento(Integer.valueOf(request.getParameter("id")));
        try {
            Evento e = dao.findById(evento);
            try (PrintWriter out = response.getWriter()) {
                out.print("<!DOCTYPE html>\n" + "<html>\n" + "    <head>\n"
                        + "        <title>Actualizar evento</title>\n" + "        <meta charset=\"utf-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                        + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\n"
                        + "              integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n"
                        + "    </head>\n" + "    <body>\n" + "        <div class=\"container\">\n"
                        + "            <div class=\"row justify-content-center\">\n"
                        + "                <div class=\"col-md-8\">\n" + "                    <div class=\"card\">\n"
                        + "                        <div class=\"card-header\">Actualizar evento</div>\n"
                        + "                        <div class=\"card-body\">\n"
                        + "                            <form method=\"POST\" action=\"EventoServlet?accion=guardar\">\n"
                        + "                                <div class=\"form-group row\">\n"
                        + "                                    <label for=\"nombreEvento\" \n"
                        + "                                           class=\"col-md-4 col-form-label text-md-right\">\n"
                        + "                                        Nombre Evento\n"
                        + "                                    </label>\n"
                        + "                                    <div class=\"col-md-6\">\n"
                        + "                                        <input \n"
                        + "                                            id=\"nombreEvento\" \n"
                        + "                                            type=\"text\" \n"
                        + "                                            class=\"form-control \"\n"
                        + "                                            name=\"nombreEvento\" \n"
                        + "                                            value='" + e.getNombreEvento() + "'\n"
                        + "                                            required \n"
                        + "                                            autofocus />\n"
                        + "                                    </div>\n" + "                                </div>\n"
                        + "                                <div class=\"form-group row\">\n"
                        + "                                    <label for=\"sede\" \n"
                        + "                                           class=\"col-md-4 col-form-label text-md-right\">\n"
                        + "                                        Sede Evento\n"
                        + "                                    </label>\n"
                        + "                                    <div class=\"col-md-6\">\n"
                        + "                                        <input \n"
                        + "                                            id=\"sede\" \n"
                        + "                                            type=\"text\" \n"
                        + "                                            class=\"form-control \"\n"
                        + "                                            name=\"sede\" \n"
                        + "                                            value='" + e.getSede() + "' \n"
                        + "                                            required \n"
                        + "                                            autofocus />\n"
                        + "                                    </div>\n" + "                                </div>\n"
                        + "                                <div class=\"form-group row\">\n"
                        + "                                    <label for=\"fechaInicio\" \n"
                        + "                                           class=\"col-md-4 col-form-label text-md-right\">\n"
                        + "                                        Fecha Inicio\n"
                        + "                                    </label>\n"
                        + "                                    <div class=\"col-md-6\">\n"
                        + "                                        <input \n"
                        + "                                            class=\"form-control\" \n"
                        + "                                            type=\"date\" \n"
                        + "                                            value=\"" + e.getFechaInicio() + ""
                        + "\"\n" + "                                            name=\"fechaInicio\"\n"
                        + "                                            id=\"fechaInicio\">\n"
                        + "                                    </div>\n" + "                                </div>\n"
                        + "                                <div class=\"form-group row\">\n"
                        + "                                    <label for=\"fechaTermino\" \n"
                        + "                                           class=\"col-md-4 col-form-label text-md-right\">\n"
                        + "                                        Fecha T&eacute;rmino\n"
                        + "                                    </label>\n"
                        + "                                    <div class=\"col-md-6\">\n"
                        + "                                        <input \n"
                        + "                                            class=\"form-control\" \n"
                        + "                                            type=\"date\" \n"
                        + "                                            value=\"" + e.getFechaTermino() + ""
                        + "\"\n" + "                                            name=\"fechaTermino\"\n"
                        + "                                            id=\"fechaTermino\">\n"
                        + "                                    </div>\n" + "                                </div>\n"
                        + "                                <div class=\"form-group row mb-0\">\n"
                        + "                                    <div class=\"col-md-6 offset-md-4\">\n"
                        + "                                        <button type=\"submit\" class=\"btn btn-primary\">\n"
                        + "                                            Actualizar\n"
                        + "                                        </button>\n"
                        + "                                    </div>\n" + "                                </div>\n"
                        + "<input type=\"hidden\" value=\"" + e.getIdEvento()
                        + "\" name=\"idEvento\"                                            id=\"fechaTermino\">\n"
                        + "                            </form>\n" + "                        </div>\n"
                        + "                    </div>\n" + "                </div>\n" + "            </div>\n"
                        + "        </div>\n"
                        + "        <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"\n"
                        + "                integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\"\n"
                        + "        crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"\n"
                        + "                integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\"\n"
                        + "        crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"\n"
                        + "                integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\"\n"
                        + "        crossorigin=\"anonymous\"></script>\n" + "    </body>\n" + "</html>\n" + "");
            }
        } catch (Exception ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //int id = Integer.parseInt(request.getParameter("id"));
        String nombreEvento = request.getParameter("nombreEvento");
        String sede = request.getParameter("sede");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaTermino = request.getParameter("fechaTermino");

        System.out.println("fechaInicio: " + fechaInicio);
        System.out.println("fechaTermino: " + fechaTermino);

        Evento e = new Evento();
        e.setNombreEvento(nombreEvento);
        e.setSede(sede);
        Date fecha = null;
        fecha = fechaInicio != null && !fechaInicio.isEmpty() ? Date.valueOf(fechaInicio) : null;
        e.setFechaInicio(fecha);
        fecha = fechaTermino != null && !fechaTermino.isEmpty() ? Date.valueOf(fechaTermino) : null;
        e.setFechaTermino(fecha);

        EventoDAO dao = new EventoDAO();
        boolean actualizado = false;

        try {
            if (request.getParameter("idEvento") != null) {
                try {
                    e.setIdEvento(Integer.valueOf(request.getParameter("idEvento")));
                    dao.update(e);
                    actualizado = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                dao.create(e);
                actualizado = false;
            }

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EventoServlet</title>");
                out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"container\">\n"
                        + "            <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n"
                        + "                <nav class=\"navbar navbar-light bg-light\">\n"
                        + "                    <a class=\"navbar-brand\" href=\"index.html\">\n"
                        + "                        <img src=\"assets/bootstrap-solid.svg\" width=\"30\" height=\"30\" class=\"d-inline-block align-top\" alt=\"\">\n"
                        + "                        Eventos\n"
                        + "                    </a>\n"
                        + "                </nav>\n"
                        + "                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                        + "                    <span class=\"navbar-toggler-icon\"></span>\n"
                        + "                </button>\n"
                        + "                <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n"
                        + "                    <ul class=\"navbar-nav\">\n"
                        + "                        <li class=\"nav-item active\">\n"
                        + "                            <a class=\"nav-link\" href=\"eventosForm.html\">Registrar Evento <span class=\"sr-only\">(current)</span></a>\n"
                        + "                        </li>\n"
                        + "                    </ul>\n"
                        + "                    <ul class=\"navbar-nav\">\n"
                        + "                        <li class=\"nav-item active\">\n"
                        + "                            <a class=\"nav-link\" href=\"EventoServlet?accion=listaDeEventos\">Lista de Eventos <span class=\"sr-only\">(current)</span></a>\n"
                        + "                        </li>\n"
                        + "                    </ul>\n"
                        + "                </div>\n"
                        + "            </nav>\n"
                        + "        </div>");
                if (!actualizado) {
                    out.println("<h1 class='text-center'>Registro Satisfactorio</h1>");
                    out.println("<div class='text-center'>");
                    out.println("<a href='eventosForm.html' class=\"btn btn-primary btn-lg\" role='button'>Registrar nuevo evento</a>");
                    out.println("<a href='EventoServlet?accion=listaDeEventos' class=\"btn btn-secondary btn-lg\" role='button'>Lista</a>");
                } else {
                    out.println("<h1 class='text-center'>Registro Actualizado</h1>");
                    out.println("<div class='text-center'>");
                    out.println("<a href='EventoServlet?accion=listaDeEventos' class=\"btn btn-primary btn-lg\" role='button'>Lista de eventos</a>");
                }

                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void mostrarEvento(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String idEvento = request.getParameter("id");
        Evento e = new Evento();
        e.setIdEvento(Integer.valueOf(idEvento));
        EventoDAO dao = new EventoDAO();
        try {
            //Evento evento = dao.findById(e.setIdEvento(Integer.valueOf(idEvento)));
            Evento evento = dao.findById(e);
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Lista de eventos</title>");
                out.println(
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"container\">\n"
                    + "            <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n"
                    + "                <nav class=\"navbar navbar-light bg-light\">\n"
                    + "                    <a class=\"navbar-brand\" href=\"index.html\">\n"
                    + "                        <img src=\"assets/bootstrap-solid.svg\" width=\"30\" height=\"30\" class=\"d-inline-block align-top\" alt=\"\">\n"
                    + "                        Eventos\n"
                    + "                    </a>\n"
                    + "                </nav>\n"
                    + "                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                    + "                    <span class=\"navbar-toggler-icon\"></span>\n"
                    + "                </button>\n"
                    + "                <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n"
                    + "                    <ul class=\"navbar-nav\">\n"
                    + "                        <li class=\"nav-item active\">\n"
                    + "                            <a class=\"nav-link\" href=\"eventosForm.html\">Registrar Evento <span class=\"sr-only\">(current)</span></a>\n"
                    + "                        </li>\n"
                    + "                    </ul>\n"
                    + "                    <ul class=\"navbar-nav\">\n"
                    + "                        <li class=\"nav-item active\">\n"
                    + "                            <a class=\"nav-link\" href=\"EventoServlet?accion=listaDeEventos\">Lista de Eventos <span class=\"sr-only\">(current)</span></a>\n"
                    + "                        </li>\n"
                    + "                    </ul>\n"
                    + "                </div>\n"
                    + "            </nav>\n"
                    + "        </div>");
                out.println("<div class='container'>");
                out.println("<h1>Datos del evento</h1>");
                out.println("<div class=\"card\">\n" + "  <h5 class=\"card-header\">Datos del evento</h5>\n"
                        + "  <div class=\"card-body\">\n" + "    <h5 class=\"card-title\">" + evento.getNombreEvento()
                        + "</h5>\n" + "    <p class=\"card-text\">Sede: " + evento.getSede() + "</p>\n"
                        + "    <p class=\"card-text\">Fecha inicio: " + evento.getFechaInicio() + "</p>\n"
                        + "    <p class=\"card-text\">Fecha término: " + evento.getFechaTermino() + "</p>\n"
                        + "    <a href=\"EventoServlet?accion=listaDeEventos\" class=\"btn btn-primary\">Volver</a>\n" + "  </div>\n" + "</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
