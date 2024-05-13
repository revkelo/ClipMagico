package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.FacturaDAO;
import com.dakin.model.Productos_ventaDAO;
import com.dakin.model.VentaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletVenta extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BdSql db = new BdSql();
		db.MySQLConnect();

		Productos_ventaDAO prod = new Productos_ventaDAO(db);
		VentaDAO venta = new VentaDAO(db);
		FacturaDAO factura = new FacturaDAO(db);
		// Obtener los valores de los parámetros
		String idProductos = req.getParameter("idProducto[]");
		String cantidadProductos = req.getParameter("cantidadProducto[]");

		// Dividir la cadena en un arreglo utilizando la coma como separador
		String[] idProductosArray = idProductos.split(",");
		String[] cantidadProductosArray = cantidadProductos.split(",");

		String idcliente = req.getParameter("idCliente");
		String total = req.getParameter("totalVenta");
		String metodo_pago = req.getParameter("metodo_pago");

		System.out.println("ID de productos:");
		for (String idProducto : idProductosArray) {
			System.out.println(idProducto);
		}

		System.out.println("Cantidad de productos:");
		for (String cantidadProducto : cantidadProductosArray) {
			System.out.println(cantidadProducto);
		}

		System.out.println("ID del cliente: " + idcliente);
		System.out.println("Total: " + total);
		System.out.println("Método de pago: " + metodo_pago);

		venta.agregarVenta(Integer.parseInt(idcliente), metodo_pago);

		for (int i = 0; i < cantidadProductosArray.length; i++) {
			System.out.println("id producto: " + idProductosArray[i] + " Cantidad: " + cantidadProductosArray[i]);
			int idventa = venta.obtenerMaximoIdVenta();
			prod.agregarProductoVenta(idventa, Integer.parseInt(idProductosArray[i]),
					Integer.parseInt(cantidadProductosArray[i]));
		}
		
//		int total1 = Math.round(Integer.parseInt(total));
//		System.out.println("totalbandera:" + total1);
//		
//		int idv = venta.obtenerMaximoIdVenta();
//		
//		factura.agregarFactura(total1,idv);
		System.out.println("Guardado");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body onload=\"showLoginError()\">  <h1>GUARDADO</h1> </body></html>");
		resp.setHeader("Refresh", "2;");

		out.close();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
