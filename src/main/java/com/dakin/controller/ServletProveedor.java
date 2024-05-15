package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;
import com.dakin.model.ProveedorDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletProveedor extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if ("DELETE".equals(request.getParameter("_method"))) {
			// Manejar la solicitud DELETE
			doDelete(request, response);
		} else if ("GET".equals(request.getParameter("_method"))) {
			// Manejar la solicitud GET
			doGet(request, response);

		} else {
			// Manejar la solicitud POST
			// doPost(request, response);
			System.out.println("jmmm");
			doPost(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("_method");
		if ("PUT".equals(method)) {
			doPut(req, resp);
		} else if ("DELETE".equals(method)) {
			doPut(req, resp);
		} else {

			System.out.println("entro");
			BdSql db = new BdSql();
			db.MySQLConnect();

			ProveedorDAO prov = new ProveedorDAO(db);

			String nombre = req.getParameter("nombre");
			String dirrecion = req.getParameter("direccion");
			String nit = req.getParameter("nit");
			String telefono = req.getParameter("telefono");

			prov.agregarProveedor(nombre, dirrecion, nit, telefono);

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

	protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("entro modificar");

		BdSql db = new BdSql();
		db.MySQLConnect();

		ProveedorDAO prov = new ProveedorDAO(db);

		int id = Integer.parseInt(req.getParameter("idProveedor"));
		String nombre = req.getParameter("nombreProvM");
		String dirrecion = req.getParameter("direccionProvM");
		String telefono = req.getParameter("telefonoProvM");
		String nit = req.getParameter("nitProvM");

		prov.actualizarProveedor(id, nombre, dirrecion, nit, telefono);
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
		try {

			System.out.println("entroal hota delete");

			System.out.println("entroeliminar");
			BdSql db = new BdSql();
			db.MySQLConnect();
			ProveedorDAO prov = new ProveedorDAO(db);

			int idprovee = Integer.parseInt(req.getParameter("idProveedor"));

			prov.eliminarProveedor(idprovee);
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			resp.getWriter().write("Eliminado");

		} catch (Exception e) {
			System.out.println("xd");
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("El proveedor que intenta eliminar se encuentra en uso");

		}
	}
}
