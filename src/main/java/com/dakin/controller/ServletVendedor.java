/**
 * Paquete controller
 */
package com.dakin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Esta clase representa un Servlet que implementa un mecanismo de autenticación
 * para un usuario administrador.
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * @author Andres
 */
public class ServletVendedor extends HttpServlet {

	/**
	 * Atributo tipo final long para el id que serializara
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constante que representn el nombre de usuario del administrador
	 */
	private static final String VEND_USERNAME = "vend";
	/**
	 * Constante que representa la contraseña del administrador
	 */
	private static final String VEND_PASSWORD = "vend";

	/**
	 * Método que maneja las solicitudes GET al Servlet. No se realiza ninguna
	 * acción en este método.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
	    // Obtener los datos del formulario
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");

	    // Verificar las credenciales del vendedor
	    if (VEND_USERNAME.equals(username) && VEND_PASSWORD.equals(password)) {
	    	resp.sendRedirect("vendedor.html");
	    } else {
	        // Si las credenciales son incorrectas, mostrar una alerta y redirigir al usuario de vuelta al formulario de inicio de sesión
	        resp.setContentType("text/html");
	        PrintWriter out = resp.getWriter();
	        out.println("<html><body onload=\"showLoginError()\">  <h1>ERROR</h1> </body></html>");
	        resp.setHeader("Refresh", "5; URL=login.jsp");

	        out.close();
	    }
	}

}