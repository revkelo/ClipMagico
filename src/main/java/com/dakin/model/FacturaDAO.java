package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FacturaDAO {

	private Connection conexion;

	public FacturaDAO(BdSql sql) {
		conexion = sql.MySQLConnect();
	}

	// Agregar producto
	public void agregarFactura(int total, int idVenta) {
		String query = "INSERT INTO Venta (total, id_venta) VALUES (?,?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, total);
			statement.setInt(2, idVenta);
			statement.executeUpdate();
			System.out.println("Factura agregada correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Eliminar producto
	public void eliminarFactura(int idFactura) {
		String query = "DELETE FROM factura WHERE id_factura = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, idFactura);
			statement.executeUpdate();
			System.out.println("Factura eliminado correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<FacturaDTO> mostrarFacturas() {
		ArrayList<FacturaDTO> facturas = new ArrayList<>();
		String query = "SELECT * FROM factura";
		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				FacturaDTO factura = new FacturaDTO(resultSet.getInt("id_factura"), resultSet.getInt("id_venta")
						, resultSet.getInt("total"));
				facturas.add(factura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facturas;
	}

}