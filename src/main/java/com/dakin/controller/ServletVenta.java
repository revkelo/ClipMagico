package com.dakin.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletVenta extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		String[] idproductos = req.getParameterValues("idProducto[]");
		String[] cantidadProductos = req.getParameterValues("cantidadProducto[]");
	
		String idcliente = req.getParameter("idCliente");
		String total = req.getParameter("total");
		String metodo_pago = req.getParameter("metodo_pago");
		String fecha = req.getParameter("fecha");
		
		if (idproductos != null) {

			for (int i = 0; i < cantidadProductos.length; i++) {
				System.out.println("id producto: " + idproductos[i]+" Cantidad: " +cantidadProductos[i]);
			}
		} else {
			System.out.println("No se recibieron datos.");
		}
		
		System.out.println(idcliente+"\n"+total+"\n"+metodo_pago+"\n"+fecha);
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

}
