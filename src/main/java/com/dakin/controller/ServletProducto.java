package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;
import com.dakin.model.ProductoDAO;
import com.dakin.model.ProductoDTO;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletProducto extends HttpServlet {

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
		System.out.println(method);
		if ("PUT".equals(method)) {
			doPut(req, resp);
		} else if ("DELETE".equals(method)) {
			doPut(req, resp);
		} else {

			BdSql db = new BdSql();
			db.MySQLConnect();
			ProductoDAO prod = new ProductoDAO(db);

			String nombre = req.getParameter("nombreProdA");
			String descripcion = req.getParameter("descripcionProdA");
			int idprov = Integer.parseInt(req.getParameter("idproveedor"));
			int cantidad = Integer.parseInt(req.getParameter("cantidadProdA"));
			int precio = Integer.parseInt(req.getParameter("precioProdA"));

			System.out.println("Nombre: " + nombre);
			System.out.println("Descripci�n: " + descripcion);
			System.out.println("ID proveedor: " + idprov);
			System.out.println("Cantidad: " + cantidad);
			System.out.println("Precio: " + precio);

			prod.agregarProducto(nombre, descripcion, idprov, cantidad, precio);

		}

	}

	protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("entro modificar");

		BdSql db = new BdSql();
		db.MySQLConnect();

		ProductoDAO prod = new ProductoDAO(db);
		int idprod = Integer.parseInt(req.getParameter("idProducto"));
		String nombre = req.getParameter("nombreProdM");
		String descripcion = req.getParameter("descripcionProdM");
		int idprov = Integer.parseInt(req.getParameter("idproveedor"));
		int cantidad = Integer.parseInt(req.getParameter("cantidadProdM"));
		int precio = Integer.parseInt(req.getParameter("precioProdM"));

		prod.actualizarProducto(idprod, nombre, descripcion, idprov, cantidad, precio);

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
		System.out.println("ELIMINAR");
		BdSql db = new BdSql();
		db.MySQLConnect();

		ProductoDAO prod = new ProductoDAO(db);

		int idprov = Integer.parseInt(req.getParameter("idProducto"));

		prod.eliminarProducto(idprov);

		System.out.println(idprov);

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		resp.getWriter().write("Eliminado");

	}

}
