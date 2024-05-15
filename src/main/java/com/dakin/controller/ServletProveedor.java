package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.ProveedorDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletProveedor  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		out.println("<html><body onload=\"showLoginError()\">  <h1>GUARDADO</h1> </body></html>");
		resp.setHeader("Refresh", "5; URL=administrador.html");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("entro");
		BdSql db = new BdSql();
		db.MySQLConnect();
		
		ProveedorDAO prov = new ProveedorDAO(db);
		
		int idproveedor = Integer.parseInt(req.getParameter("idProveedor"));
		prov.eliminarProveedor(idproveedor);
		
		out.println("<html><body onload=\"showLoginError()\">  <h1>ELIMINADO</h1> </body></html>");
		resp.setHeader("Refresh", "5; URL=administrador.html");
	}
}
