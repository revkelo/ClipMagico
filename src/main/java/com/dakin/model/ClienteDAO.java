package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

	private Connection conexion;

	public ClienteDAO(BdSql sql) {
		conexion = sql.MySQLConnect();
	}

	// Agregar cliente
	public void agregarCliente(String nombre, int cedula, String direccion, String telefonoC) {

		String query = "INSERT INTO Cliente (cedula, nombre, direccion, telefonoC) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, cedula);
			statement.setString(2, nombre);
			statement.setString(3, direccion);
			statement.setString(4, telefonoC);
			statement.executeUpdate();
			System.out.println("Cliente agregado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Eliminar cliente
	public void eliminarCliente(int cedula) {

		String query = "DELETE FROM Cliente WHERE cedula = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, cedula);
			statement.executeUpdate();
			System.out.println("Cliente eliminado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ClienteDTO> mostrarClientes() {
	    ArrayList<ClienteDTO> clientes = new ArrayList<>();

	    String query = "SELECT * FROM Cliente";
	    try (PreparedStatement statement = conexion.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {
	        while (resultSet.next()) {
	            ClienteDTO cliente = new ClienteDTO(resultSet.getInt("id_cliente"),
	                    resultSet.getInt("cedula"),
	                    resultSet.getString("nombre"),
	                    resultSet.getString("direccion"),
	                    resultSet.getString("telefonoC"));
	            clientes.add(cliente);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return clientes;
	}

	// Actualizar cliente
	public void actualizarCliente(int cedula, String nombre, String direccion, String telefonoC) {

		String query = "UPDATE Cliente SET cedula = ?, nombre = ?, direccion = ?, telefonoC = ? WHERE cedula = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, cedula);
			statement.setString(2, nombre);
			statement.setString(3, direccion);
			statement.setString(4, telefonoC);
			
			statement.executeUpdate();
			System.out.println("Cliente actualizado correctamente.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}