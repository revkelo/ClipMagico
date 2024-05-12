package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO(BdSql sql) {
        conexion = sql.MySQLConnect();
    }

    // Agregar producto
    public void agregarProducto(String nombre, String descripcion, int idProveedor, int precio) {
        String query = "INSERT INTO Producto (nombre, descripcion, id_proveedor, precio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setInt(3, idProveedor);
            statement.setInt(4, precio);
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
    
    public ProductoDTO obtenerProductoPorId(int idProducto) {
        ProductoDTO producto = null;
        String query = "SELECT * FROM Producto WHERE id_producto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idProducto);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new ProductoDTO(
                            resultSet.getInt("id_producto"),
                            resultSet.getString("nombre"),
                            resultSet.getString("descripcion"),
                            resultSet.getInt("id_proveedor"),
                            resultSet.getInt("precio")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Mostrar producto
    public ArrayList<ProductoDTO> mostrarProductos() {
        ArrayList<ProductoDTO> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto";
        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductoDTO producto = new ProductoDTO(
                        resultSet.getInt("id_producto"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("id_proveedor"),
                        resultSet.getInt("precio")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }


    // Actualizar producto
    public void actualizarProducto(int idProducto, String nombre, String descripcion, int idProveedor, int precio) {
        String query = "UPDATE Producto SET nombre = ?, descripcion = ?, id_proveedor = ?, precio = ? WHERE id_producto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setInt(3, idProveedor);
            statement.setInt(4, idProducto);
            statement.setInt(5, precio);
            statement.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
