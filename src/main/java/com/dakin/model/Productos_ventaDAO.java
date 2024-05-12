package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Productos_ventaDAO {

	private Connection conexion;

	public Productos_ventaDAO(BdSql sql) {
		conexion = sql.MySQLConnect();
	}

	// Agregar producto
	public void agregarProductoVenta(int idVenta, int idProducto, int cantidad) {
		String query = "INSERT INTO producto_venta (id_venta, id_producto, cantidad) VALUES (?, ?, ?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idVenta);
			statement.setInt(2, idProducto);
			statement.setInt(3, cantidad);
			statement.executeUpdate();
			System.out.println("Producto_venta agregado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Eliminar producto
	public void eliminarProductoVenta(int idVenta) {
		String query = "DELETE FROM prodcuto_venta WHERE id_venta = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idVenta);
			statement.executeUpdate();
			System.out.println("Producto_Venta eliminado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Productos_ventaDTO> mostrarProductoVenta() {
		ArrayList<Productos_ventaDTO> productos_venta = new ArrayList<>();
		String query = "SELECT * FROM produto_venta";
		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Productos_ventaDTO productos_ventas = new Productos_ventaDTO(resultSet.getInt("id_venta"), resultSet.getInt("id_producto")
						, resultSet.getInt("cantidad"));
				productos_venta.add(productos_ventas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos_venta;
	}

	// Actualizar producto
	public void actualizarProductoVenta(int idVenta, int idProducto, int cantidad) {
		String query = "UPDATE producto_venta SET id_venta = ?, id_producto = ?, cantidad = ?, metodo_pago = ? WHERE id_venta = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idVenta);
			statement.setInt(2, idProducto);
			statement.setInt(3, cantidad);
			statement.executeUpdate();
			System.out.println("Producto_venta actualizado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}