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
	public void agregarVenta(int idCliente, int total, String metodo_pago) {
		Date date = new Date();
		String query = "INSERT INTO Venta (id_cliente, total, metodo_pago, fecha) VALUES (?,?,?,?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idCliente);
			statement.setInt(3, total);
			statement.setString(5, metodo_pago);
			statement.setDate(6, new java.sql.Date(date.getTime()));
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
				VentaDTO venta = new VentaDTO(resultSet.getInt("id_venta"), resultSet.getInt("id_cliente")
						, resultSet.getInt("total"),
						resultSet.getString("metodo_pago"), resultSet.getDate("fecha"));
				ventas.add(venta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ventas;
	}

	// Actualizar producto
	public void actualizarProducto(int idCliente, int total, String metodo_pago) {
		String query = "UPDATE Venta SET id_cliente = ?, total = ?, metodo_pago = ? WHERE id_producto = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idCliente);
			statement.setInt(3, total);
			statement.setString(5, metodo_pago);
			statement.executeUpdate();
			System.out.println("Producto actualizado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}