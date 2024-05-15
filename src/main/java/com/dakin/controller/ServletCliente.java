package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletCliente extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("DELETE".equals(request.getParameter("_method"))) {
            // Manejar la solicitud DELETE
            doDelete(request, response);
        } else if ("GET".equals(request.getParameter("_method"))) {
            // Manejar la solicitud GET
            doGet(request, response);
        } else {
            // Manejar la solicitud POST
            System.out.println("jmmm");
            doPost(request, response);
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("PUT".equals(method)) {
            doPut(req, resp);
        } else if ("DELETE".equals(method)) {
        	doPut(req, resp);
        } else {
            // Manejar la solicitud POST
            System.out.println("jmmm");
            BdSql db = new BdSql();
            db.MySQLConnect();
            ClienteDAO cliente = new ClienteDAO(db);

            int cedula = Integer.parseInt(req.getParameter("cedulaClienteAgregar"));
            String nombre = req.getParameter("nombreClienteAgregar");
            String direccion = req.getParameter("direccionClienteAgregar");
            String telefonoC = req.getParameter("telefonoClienteAgregar");

            cliente.agregarCliente(nombre, cedula, direccion, telefonoC);
            
    		resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"es\">");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.println("<title>Guardado</title>");
			out.println(
					"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container mt-5\">");
			out.println("<h1 class=\"text-center text-success\">Guardado</h1>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

			out.close();
            
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("entro modificar");

        BdSql db = new BdSql();
        db.MySQLConnect();

        ClienteDAO cliente = new ClienteDAO(db);

        int cedula = Integer.parseInt(req.getParameter("idCliente"));
        String nombre = req.getParameter("nombreClienteM");
        String direccion = req.getParameter("direccionClienteM");
        String telefonoC = req.getParameter("telefonoClienteM");

        cliente.actualizarCliente(cedula, nombre, direccion, telefonoC);
        System.out.println("Actualizado");
        
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"es\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>Guardado</title>");
		out.println(
				"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container mt-5\">");
		out.println("<h1 class=\"text-center text-success\">Actualizado</h1>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

		out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("entroeliminar");
        BdSql db = new BdSql();
        db.MySQLConnect();
        ClienteDAO cliente = new ClienteDAO(db);

        int cedula = Integer.parseInt(req.getParameter("cedula"));

        cliente.eliminarCliente(cedula);
        
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"es\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>Guardado</title>");
		out.println(
				"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container mt-5\">");
		out.println("<h1 class=\"text-center text-success\">Eliminado</h1>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

		out.close();
    }

}
