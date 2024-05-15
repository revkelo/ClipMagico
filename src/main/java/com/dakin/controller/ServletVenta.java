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

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BdSql db = new BdSql();
			db.MySQLConnect();

			Productos_ventaDAO prod = new Productos_ventaDAO(db);
			VentaDAO venta = new VentaDAO(db);

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
			System.out.println("Guardado");

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

		} catch (NumberFormatException e) {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"es\">");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.println("<title>Error</title>");
			out.println(
					"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container mt-5\">");
			out.println("<h1 class=\"text-center text-danger\">Error</h1>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

			out.close();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
