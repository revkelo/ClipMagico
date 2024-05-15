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

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("El proveedor que intenta eliminar se encuentra en uso");

		}
	}
}
