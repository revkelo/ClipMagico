package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.ProductoDAO;
import com.dakin.model.ProductoDTO;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletProducto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BdSql db = new BdSql();
		db.MySQLConnect();
		ProductoDAO prod = new ProductoDAO(db);

		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		int idprov = Integer.parseInt(req.getParameter("idproveedor"));
		int cantidad = Integer.parseInt(req.getParameter("cantidad"));
		int precio = Integer.parseInt(req.getParameter("precio"));

		System.out.println("Nombre: " + nombre);
		System.out.println("Descripci�n: " + descripcion);
		System.out.println("ID proveedor: " + idprov);
		System.out.println("Cantidad: " + cantidad);
		System.out.println("Precio: " + precio);

		prod.agregarProducto(nombre, descripcion, idprov, cantidad, precio);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body onload=\"showLoginError()\">  <h1>GUARDADO</h1> </body></html>");

		out.close();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BdSql db = new BdSql();
		ProductoDAO dao = new ProductoDAO(db);
		String productId = req.getParameter("id");

		try {
			// Convertir el ID de String a int
			int idProducto = Integer.parseInt(productId);

			// Obtener el ProductoDTO correspondiente al ID
			ProductoDTO producto = dao.obtenerProductoPorId(idProducto);

			if (producto != null) {
				// Convertir el producto a JSON utilizando Gson
				String productoJson = convertirProductoAJson(producto);

				// Establecer el tipo de contenido de la respuesta como application/json
				resp.setContentType("application/json");

				// Escribir la respuesta JSON en el cuerpo de la respuesta
				resp.getWriter().write(productoJson);
			} else {
				// Si no se encuentra el producto, enviar un mensaje de error al cliente
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				resp.getWriter().write("No se encontró ningún producto con el ID proporcionado.");
			}
		} catch (NumberFormatException e) {
			// Manejar la excepción si el ID proporcionado no es un número válido
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("El ID del producto proporcionado no es válido.");
		}
	}

	// Método para convertir un objeto ProductoDTO a JSON utilizando Gson
	private String convertirProductoAJson(ProductoDTO producto) {
		Gson gson = new Gson();
		return gson.toJson(producto);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BdSql db = new BdSql();
		db.MySQLConnect();

		ProductoDAO prod = new ProductoDAO(db);

		int idprov = Integer.parseInt(req.getParameter("idProducto"));

		prod.eliminarProducto(idprov);

		System.out.println(idprov);

	}

}
