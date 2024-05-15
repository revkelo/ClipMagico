package com.dakin.controller;

import java.io.IOException;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletCliente extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("entro");
		BdSql db = new BdSql();
		db.MySQLConnect();
		ClienteDAO cliente = new ClienteDAO(db);
		
		int cedula = Integer.parseInt(req.getParameter("cedulaClienteAgregar"));
	    String nombre = req.getParameter("nombreClienteAgregar");
	    String direccion = req.getParameter("direccionClienteAgregar");
	    String telefonoC = req.getParameter("telefonoClienteAgregar");
	    
	    cliente.agregarCliente(nombre,cedula ,direccion, telefonoC);

	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("entroeliminar");
		BdSql db = new BdSql();
		db.MySQLConnect();
		ClienteDAO cliente = new ClienteDAO(db);
		
		int cedula = Integer.parseInt(req.getParameter("cedula"));
		
		cliente.eliminarCliente(cedula);
		
	}

}
