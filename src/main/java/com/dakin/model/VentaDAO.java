package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VentaDAO {

	private Connection conexion;

	public VentaDAO(BdSql sql) {
		conexion = sql.MySQLConnect();
	}

	// Agregar producto
	public void agregarVenta(int idCliente, String metodo_pago) {
		Date date = new Date();
		String query = "INSERT INTO Venta (id_cliente, metodo_pago, fecha) VALUES (?,?,?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idCliente);
			statement.setString(2, metodo_pago);
			statement.setDate(3, new java.sql.Date(date.getTime()));
			statement.executeUpdate();
			System.out.println("Producto agregado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Eliminar producto
	public void eliminarProducto(int idProducto) {
		String query = "DELETE FROM Venta WHERE id_venta = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idProducto);
			statement.executeUpdate();
			System.out.println("Venta eliminado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<VentaDTO> mostrarVentas() {
		ArrayList<VentaDTO> ventas = new ArrayList<>();
		String query = "SELECT * FROM Venta";
		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				VentaDTO venta = new VentaDTO(resultSet.getInt("id_venta"), resultSet.getInt("id_cliente"),
						resultSet.getString("metodo_pago"), resultSet.getDate("fecha"));
				ventas.add(venta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ventas;
	}

	public int obtenerMaximoIdVenta() {
	    int maxIdVenta = 0;
	    String query = "SELECT MAX(id_venta) AS max_id_venta FROM Venta";
	    try (PreparedStatement statement = conexion.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {
	        if (resultSet.next()) {
	            maxIdVenta = resultSet.getInt("max_id_venta");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maxIdVenta;
	}

	// Actualizar producto
	public void actualizarProducto(int idCliente, String metodo_pago) {
		String query = "UPDATE Venta SET id_cliente = ?, metodo_pago = ? WHERE id_producto = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idCliente);
			statement.setString(5, metodo_pago);
			statement.executeUpdate();
			System.out.println("Producto actualizado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}