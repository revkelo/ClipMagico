package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorDAO {
  
	 private Connection conexion;
	 
	 public ProveedorDAO(BdSql sql) {
	        conexion = sql.MySQLConnect();
	    }
	 
//	(+) agregarProveedor(idProveedor: int, nombre: String, direccion: String, telefono: String, NIT: String)
	 public void agregarProveedor(String nombre, String direccion, String NIT, String telefonoP) {
	        String query = "INSERT INTO Proveedor (nombre, direccion, NIT, telefonoP) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	            statement.setString(1, nombre);
	            statement.setString(2, direccion);
	            statement.setString(3, NIT);
	            statement.setString(4, telefonoP);
	            statement.executeUpdate();
	            System.out.println("Proveedor agregado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
//	(-) eliminarProveedor(idProveedor: int)

	 public void eliminarProveedor(int idProveedor) {
	        String query = "DELETE FROM Proveedor WHERE id_proveedor = ?";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	            statement.setInt(1, idProveedor);
	            statement.executeUpdate();
	            System.out.println("Proveedor eliminado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 
//	(+) mostrarProveedor(idProveedor: int)

	 public ProveedorDTO mostrarProveedor(int idProveedor) {
		 	ProveedorDTO proveedor = null;
	        String query = "SELECT * FROM Proveedor WHERE id_proveedor = ?";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	            statement.setInt(1, idProveedor);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                	proveedor = new ProveedorDTO(resultSet.getInt("id_proveedor"), resultSet.getString("nombre"),
	                            resultSet.getString("direccion"), resultSet.getString("NIT"), resultSet.getString("telefonoP"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return proveedor;
	    }
	 
//	(+) actualizarProveedor(idProveedor: int, nombre: String, direccion: String, telefono: String, NIT: String)

	 public void actualizarProveedor(int idProveedor, String nombre, String direccion,  String NIT, String telefonoP) {
	        String query = "UPDATE Proveedor SET nombre = ?, direccion = ?, NIT = ?, telefonoP = ? WHERE id_proveedor = ?";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	            statement.setString(1, nombre);
	            statement.setString(2, direccion);
	            statement.setString(3, NIT);
	            statement.setString(4, telefonoP);
	            statement.setInt(5, idProveedor);
	            statement.executeUpdate();
	            System.out.println("Proveedor actualizado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}