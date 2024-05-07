package com.dakin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ClienteDAO {

    private BdSql sql = new BdSql();

    // Agregar cliente
    public void agregarCliente(int idCliente, String nombre, String direccion, String telefonoC) {
        try (Connection conexion = sql.MySQLConnect()) {
            String query = "INSERT INTO Cliente (id_cliente, nombre, direccion, telefonoC) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, idCliente);
                statement.setString(2, nombre);
                statement.setString(3, direccion);
                statement.setString(4, telefonoC);
                statement.executeUpdate();
                System.out.println("Cliente agregado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar cliente
    public void eliminarCliente(int idCliente) {
        try (Connection conexion = sql.MySQLConnect()) {
            String query = "DELETE FROM Cliente WHERE id_cliente = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, idCliente);
                statement.executeUpdate();
                System.out.println("Cliente eliminado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mostrar cliente
    public ClienteDTO mostrarCliente(int idCliente) {
        ClienteDTO cliente = null;
        try (Connection conexion = sql.MySQLConnect()) {
            String query = "SELECT * FROM Cliente WHERE id_cliente = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, idCliente);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        cliente = new ClienteDTO(
                            resultSet.getInt("id_cliente"),
                            resultSet.getString("nombre"),
                            resultSet.getString("direccion"),
                            resultSet.getString("telefonoC")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Actualizar cliente
    public void actualizarCliente(int idCliente, String nombre, String direccion, String telefonoC) {
        try (Connection conexion = sql.MySQLConnect()) {
            String query = "UPDATE Cliente SET nombre = ?, direccion = ?, telefonoC = ? WHERE id_cliente = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, nombre);
                statement.setString(2, direccion);
                statement.setString(3, telefonoC);
                statement.setInt(4, idCliente);
                statement.executeUpdate();
                System.out.println("Cliente actualizado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
