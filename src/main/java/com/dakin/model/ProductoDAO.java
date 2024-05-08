package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO(BdSql sql) {
        conexion = sql.MySQLConnect();
    }

    // Agregar producto
    public void agregarProducto(String nombre, String descripcion, int idProveedor) {
        String query = "INSERT INTO Producto (nombre, descripcion, id_proveedor) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setInt(3, idProveedor);
            statement.executeUpdate();
            System.out.println("Producto agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar producto
    public void eliminarProducto(int idProducto) {
        String query = "DELETE FROM Producto WHERE id_producto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idProducto);
            statement.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mostrar producto
    public ProductoDTO mostrarProducto(int idProducto) {
        ProductoDTO producto = null;
        String query = "SELECT * FROM Producto WHERE id_producto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idProducto);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new ProductoDTO(resultSet.getInt("id_producto"), resultSet.getString("nombre"),
                            resultSet.getString("descripcion"), resultSet.getInt("id_proveedor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Actualizar producto
    public void actualizarProducto(int idProducto, String nombre, String descripcion, int idProveedor) {
        String query = "UPDATE Producto SET nombre = ?, descripcion = ?, id_proveedor = ? WHERE id_producto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setInt(3, idProveedor);
            statement.setInt(4, idProducto);
            statement.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
