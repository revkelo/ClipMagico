package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.Productos_ventaDAO;
import com.dakin.model.VentaDAO;

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

		BdSql db = new BdSql();
		db.MySQLConnect();

		Productos_ventaDAO prod = new Productos_ventaDAO(db);
		VentaDAO venta = new VentaDAO(db);

		String[] idproductos = req.getParameterValues("idProducto[]");
		String[] cantidadProductos = req.getParameterValues("cantidadProducto[]");

		String idcliente = req.getParameter("idCliente");
		String total = req.getParameter("total");
		String metodo_pago = req.getParameter("metodo_pago");

		venta.agregarVenta(Integer.parseInt(idcliente), metodo_pago);

		for (int i = 0; i < cantidadProductos.length; i++) {
			System.out.println("id producto: " + idproductos[i] + " Cantidad: " + cantidadProductos[i]);
			int idventa = venta.obtenerMaximoIdVenta();
			prod.agregarProductoVenta(idventa, Integer.parseInt(idproductos[i]),
					Integer.parseInt(cantidadProductos[i]));
		}
		System.out.println("Guardado");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body onload=\"showLoginError()\">  <h1>GUARDADO</h1> </body></html>");
		resp.setHeader("Refresh", "5; URL=login.jsp");

		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

}
