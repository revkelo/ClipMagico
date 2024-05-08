package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public class VentaDAO {

	  private Connection conexion;
	  
	    public VentaDAO(BdSql sql) {
	        conexion = sql.MySQLConnect();
	    }

	    // Agregar producto
	    public void agregarVenta(int idCliente,int idProducto, int total, int cantidad, String metodo_pago) {
	    	Date date = new Date();
	        String query = "INSERT INTO Venta (id_cliente, id_producto, total, cantidad, metodo_pago, fecha) VALUES (?, ?, ?,?,?,?)";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	            statement.setInt(1, idCliente);
	            statement.setInt(2, idProducto);
	            statement.setInt(3, total);
	            statement.setInt(4, cantidad);
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

	    // Mostrar producto
	    public VentaDTO mostrarVenta(int idVenta) {
	        VentaDTO venta = null;
	        String query = "SELECT * FROM Venta WHERE id_venta = ?";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	            statement.setInt(1, idVenta);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    venta = new VentaDTO(resultSet.getInt("id_venta"), resultSet.getInt("id_cliente"),
	                    		resultSet.getInt("id_producto"),resultSet.getInt("total"), resultSet.getInt("cantidad"),resultSet.getString("metodo_pago"),resultSet.getDate("fecha"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return venta;
	    }

	    // Actualizar producto
	    public void actualizarProducto(int idCliente,int idProducto, int total, int cantidad, String metodo_pago) {
	        String query = "UPDATE Venta SET id_cliente = ?, id_producto = ?, total = ?, cantidad = ?, metodo_pago = ? WHERE id_producto = ?";
	        try (PreparedStatement statement = conexion.prepareStatement(query)) {
	        	statement.setInt(1, idCliente);
	            statement.setInt(2, idProducto);
	            statement.setInt(3, total);
	            statement.setInt(4, cantidad);
	            statement.setString(5, metodo_pago);
	            statement.executeUpdate();
	            System.out.println("Producto actualizado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }