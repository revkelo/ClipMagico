package com.dakin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BdSql {

    private Connection conexion = null;
    private Statement comando = null;

    public Connection MySQLConnect() {
        try {
            // Driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            // URL de conexión a la base de datos
            String servidor = "jdbc:mysql://monorail.proxy.rlwy.net:26721/railway";
            String usuario = "root";
            String pass = "OhxcmkCNBjWBntPCYFuLWRKKlOvwcPGg";
            // Iniciar la conexión
            conexion = DriverManager.getConnection(servidor, usuario, pass);
            System.out.println("Conexión a MySQL exitosa");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        }
        return conexion;
    }
    
    public void createTables() {
        try {
   
            if (conexion != null) {
                comando = conexion.createStatement();
                // Crear tabla Cliente
                comando.executeUpdate("CREATE TABLE IF NOT EXISTS Cliente (id_cliente INT PRIMARY KEY, nombre VARCHAR(255), direccion VARCHAR(255), telefonoC VARCHAR(10))");
                // Crear tabla Proveedor
                comando.executeUpdate("CREATE TABLE IF NOT EXISTS Proveedor (id_proveedor INT PRIMARY KEY, nombre VARCHAR(255), direccion VARCHAR(255), NIT VARCHAR(20), telefonoP VARCHAR(10))");
                // Crear tabla Producto
                comando.executeUpdate("CREATE TABLE IF NOT EXISTS Producto (id_producto INT PRIMARY KEY, nombre VARCHAR(255), descripcion TEXT, id_proveedor INT, FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor))");
                // Crear tabla Venta
                comando.executeUpdate("CREATE TABLE IF NOT EXISTS Venta (id_venta INT PRIMARY KEY, id_cliente INT, id_producto INT, total INT, cantidad INT, FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente), FOREIGN KEY (id_producto) REFERENCES Producto(id_producto))");
                // Crear tabla Inventario
                comando.executeUpdate("CREATE TABLE IF NOT EXISTS Inventario (id_inventario INT PRIMARY KEY, id_producto INT, cantidad INT, fecha_actualizacion DATE, FOREIGN KEY (id_producto) REFERENCES Producto(id_producto))");
                System.out.println("Tablas creadas correctamente.");
            } else {
                System.out.println("No se puede crear las tablas. No hay conexión establecida.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar la conexión y el statement
            try {
                if (comando != null) comando.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
