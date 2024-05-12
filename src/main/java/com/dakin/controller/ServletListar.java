package com.dakin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;
import com.dakin.model.ClienteDTO;
import com.dakin.model.FacturaDAO;
import com.dakin.model.FacturaDTO;
import com.dakin.model.ProductoDAO;
import com.dakin.model.ProductoDTO;
import com.dakin.model.Productos_ventaDAO;
import com.dakin.model.Productos_ventaDTO;
import com.dakin.model.ProveedorDAO;
import com.dakin.model.ProveedorDTO;
import com.dakin.model.VentaDAO;
import com.dakin.model.VentaDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * 
 * Esta clase implementa un servlet que se utiliza para listar en un tabla por
 * carrera los Aspirantes
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * @author Andres
 */

public class ServletListar extends HttpServlet {

	/**
	 * Atributo tipo final long para el id que serializara
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que maneja la petición GET y muestra la información de los aspirantes
	 * en una tabla HTML por carrera.
	 * 
	 * @param req  objeto HttpServletRequest que contiene la información de la
	 *             solicitud del cliente.
	 * 
	 * @param resp objeto HttpServletResponse que contiene la información de la
	 *             respuesta del servidor.
	 * 
	 * @throws ServletException si ocurre un error en el servlet.
	 * 
	 * @throws IOException      si ocurre un error de entrada/salida al manejar la
	 *                          petición.
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		BdSql db = new BdSql();
		db.MySQLConnect();
		String mostrar = req.getParameter("mostrar");
		System.out.println(mostrar);
		PrintWriter salida = resp.getWriter();
		if (mostrar.equals("cliente")) {

			ClienteDAO cli = new ClienteDAO(db);
			ArrayList<ClienteDTO> listaClientes = cli.mostrarClientes(); // Método para obtener clientes de la base de
																			// datos

			salida.println("<!DOCTYPE html>");
			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta charset=\"UTF-8\" />");
			salida.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
			salida.println("<title>Clientes</title>");
			salida.println(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" />");
			salida.println("</head>");
			salida.println("<body>");
			salida.println("<h1 class=\"display-4\">Lista de Clientes</h1>");
			salida.println("<table class=\"table table-bordered border-primary\">");
			salida.println("<thead>");
			salida.println("<tr>");
			salida.println("<th>ID</th>");
			salida.println("<th>Nombre</th>");
			salida.println("<th>Dirección</th>");
			salida.println("<th>Teléfono</th>");
			salida.println("</tr>");
			salida.println("</thead>");
			salida.println("<tbody>");

			for (ClienteDTO cliente : listaClientes) {
				salida.println("<tr>");
				salida.println("<td>" + cliente.getIdCliente() + "</td>");
				salida.println("<td>" + cliente.getNombre() + "</td>");
				salida.println("<td>" + cliente.getDireccion() + "</td>");
				salida.println("<td>" + cliente.getTelefonoC() + "</td>");
				salida.println("</tr>");
			}

			salida.println("</tbody>");
			salida.println("</table>");
			salida.println("</body>");
			salida.println("</html>");

			salida.close();
		} else if (mostrar.equals("proveedor")) {

			ProveedorDAO proveedor1 = new ProveedorDAO(db);
			ArrayList<ProveedorDTO> listaProveedores = proveedor1.mostrarProveedores(); // Método para obtener
																						// proveedores de la base de
																						// datos

			salida.println("<!DOCTYPE html>");
			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta charset=\"UTF-8\" />");
			salida.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
			salida.println("<title>Proveedores</title>");
			salida.println(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" />");
			salida.println("</head>");
			salida.println("<body>");
			salida.println("<h1 class=\"display-4\">Lista de Proveedores</h1>");
			salida.println("<table class=\"table table-bordered border-primary\">");
			salida.println("<thead>");
			salida.println("<tr>");
			salida.println("<th>ID Proveedor</th>");
			salida.println("<th>Nombre</th>");
			salida.println("<th>Dirección</th>");
			salida.println("<th>NIT</th>");
			salida.println("<th>Teléfono</th>");
			salida.println("</tr>");
			salida.println("</thead>");
			salida.println("<tbody>");

			for (ProveedorDTO proveedor : listaProveedores) {
				salida.println("<tr>");
				salida.println("<td>" + proveedor.getIdProveedor() + "</td>");
				salida.println("<td>" + proveedor.getNombre() + "</td>");
				salida.println("<td>" + proveedor.getDireccion() + "</td>");
				salida.println("<td>" + proveedor.getNIT() + "</td>");
				salida.println("<td>" + proveedor.getTelefonoP() + "</td>");
				salida.println("</tr>");
			}

			salida.println("</tbody>");
			salida.println("</table>");
			salida.println("</body>");
			salida.println("</html>");

			salida.close();

		} else if (mostrar.equals("venta")) {

			VentaDAO ven = new VentaDAO(db);

			ArrayList<VentaDTO> listaVentas = ven.mostrarVentas(); // Método para obtener ventas de la base de datos

			salida.println("<!DOCTYPE html>");
			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta charset=\"UTF-8\" />");
			salida.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
			salida.println("<title>Ventas</title>");
			salida.println(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" />");
			salida.println("</head>");
			salida.println("<body>");
			salida.println("<h1 class=\"display-4\">Lista de Ventas</h1>");
			salida.println("<table class=\"table table-bordered border-primary\">");
			salida.println("<thead>");
			salida.println("<tr>");
			salida.println("<th>ID Venta</th>");
			salida.println("<th>ID Cliente</th>");
			salida.println("<th>Método de Pago</th>");
			salida.println("<th>Fecha</th>");
			salida.println("</tr>");
			salida.println("</thead>");
			salida.println("<tbody>");

			for (VentaDTO venta : listaVentas) {
				salida.println("<tr>");
				salida.println("<td>" + venta.getIdVenta() + "</td>");
				salida.println("<td>" + venta.getIdCliente() + "</td>");
				salida.println("<td>" + venta.getMetodo_pago() + "</td>");
				salida.println("<td>" + venta.getFecha() + "</td>");
				salida.println("</tr>");
			}

			salida.println("</tbody>");
			salida.println("</table>");
			salida.println("</body>");
			salida.println("</html>");

			salida.close();

		} else if (mostrar.equals("producto_venta")) {

			Productos_ventaDAO pv = new Productos_ventaDAO(db);
			ArrayList<Productos_ventaDTO> listaProductosv = pv.mostrarProductoVenta(); // Método para obtener productos
																			// de la base de datos

			salida.println("<!DOCTYPE html>");
			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta charset=\"UTF-8\" />");
			salida.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
			salida.println("<title>Productos</title>");
			salida.println(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" />");
			salida.println("</head>");
			salida.println("<body>");
			salida.println("<h1 class=\"display-4\">Lista de Productos</h1>");
			salida.println("<table class=\"table table-bordered border-primary\">");
			salida.println("<thead>");
			salida.println("<tr>");
			salida.println("<th>ID Venta</th>");
			salida.println("<th>ID Producto</th>");
			salida.println("<th>Cantidad</th>");
			salida.println("</tr>");
			salida.println("</thead>");
			salida.println("<tbody>");

			for (Productos_ventaDTO producto : listaProductosv) {
				salida.println("<tr>");
				salida.println("<td>" + producto.getIdVenta() + "</td>");
				salida.println("<td>" + producto.getIdProducto() + "</td>");
				salida.println("<td>" + producto.getCantidad() + "</td>");
				salida.println("</tr>");
			}

			salida.println("</tbody>");
			salida.println("</table>");
			salida.println("</body>");
			salida.println("</html>");

			salida.close();

		} else if (mostrar.equals("producto")) {

			ProductoDAO pro = new ProductoDAO(db);
			ArrayList<ProductoDTO> listaProductos = pro.mostrarProductos(); // Método para obtener productos
																			// de la base de datos

			salida.println("<!DOCTYPE html>");
			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta charset=\"UTF-8\" />");
			salida.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
			salida.println("<title>Productos</title>");
			salida.println(
					"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" />");
			salida.println("</head>");
			salida.println("<body>");
			salida.println("<h1 class=\"display-4\">Lista de Productos</h1>");
			salida.println("<table class=\"table table-bordered border-primary\">");
			salida.println("<thead>");
			salida.println("<tr>");
			salida.println("<th>ID Producto</th>");
			salida.println("<th>Nombre</th>");
			salida.println("<th>Descripción</th>");
			salida.println("<th>ID Proveedor</th>");
			salida.println("<th>Cantidad</th>");
			salida.println("<th>Precio</th>");
			salida.println("</tr>");
			salida.println("</thead>");
			salida.println("<tbody>");

			for (ProductoDTO producto : listaProductos) {
				salida.println("<tr>");
				salida.println("<td>" + producto.getIdProducto() + "</td>");
				salida.println("<td>" + producto.getNombre() + "</td>");
				salida.println("<td>" + producto.getDescripcion() + "</td>");
				salida.println("<td>" + producto.getIdProveedor() + "</td>");
				salida.println("<td>" + producto.getCantidad() + "</td>");
				salida.println("<td>" + producto.getPrecio() + "</td>");
				salida.println("</tr>");
			}

			salida.println("</tbody>");
			salida.println("</table>");
			salida.println("</body>");
			salida.println("</html>");

			salida.close();

			
		}else if (mostrar.equals("factura")) {

				FacturaDAO fa = new FacturaDAO(db);
				ArrayList<FacturaDTO> listafactura = fa.mostrarFacturas(); // Método para obtener productos
																				// de la base de datos

				salida.println("<!DOCTYPE html>");
				salida.println("<html>");
				salida.println("<head>");
				salida.println("<meta charset=\"UTF-8\" />");
				salida.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
				salida.println("<title>Productos</title>");
				salida.println(
						"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" />");
				salida.println("</head>");
				salida.println("<body>");
				salida.println("<h1 class=\"display-4\">Lista de Productos</h1>");
				salida.println("<table class=\"table table-bordered border-primary\">");
				salida.println("<thead>");
				salida.println("<tr>");
				salida.println("<th>ID Factura</th>");
				salida.println("<th>ID Venta</th>");
				salida.println("<th>Total</th>");

				salida.println("</tr>");
				salida.println("</thead>");
				salida.println("<tbody>");

				for (FacturaDTO facturas : listafactura) {
					salida.println("<tr>");
					salida.println("<td>" + facturas.getIdFactura() + "</td>");
					salida.println("<td>" + facturas.getIdVenta() + "</td>");
					salida.println("<td>" + facturas.getTotal() + "</td>");

					salida.println("</tr>");
				}

				salida.println("</tbody>");
				salida.println("</table>");
				salida.println("</body>");
				salida.println("</html>");

				salida.close();
		} else {


		}

	}

	/**
	 * 
	 * Método que se ejecuta al enviar el formulario con método POST.
	 * 
	 * Recibe un objeto HttpServletRequest con la información de la solicitud HTTP
	 * 
	 * y un objeto HttpServletResponse con la información de la respuesta HTTP.
	 * 
	 * @param req  objeto HttpServletRequest con la información de la solicitud
	 *             HTTP.
	 * 
	 * @param resp objeto HttpServletResponse con la información de la respuesta
	 *             HTTP.
	 * 
	 * @throws ServletException si ocurre un error en la ejecución del servlet.
	 * 
	 * @throws IOException      si ocurre un error en la entrada o salida de datos.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
