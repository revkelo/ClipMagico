-- ClipMagico — Schema de base de datos
-- Ejecutar en MySQL antes de desplegar la aplicación

CREATE DATABASE IF NOT EXISTS clipmagico
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE clipmagico;

-- Proveedor
CREATE TABLE IF NOT EXISTS Proveedor (
    id_proveedor INT PRIMARY KEY AUTO_INCREMENT,
    nombre       VARCHAR(255) NOT NULL,
    direccion    VARCHAR(255),
    NIT          VARCHAR(20),
    telefonoP    VARCHAR(10)
);

-- Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre     VARCHAR(255) NOT NULL,
    direccion  VARCHAR(255),
    telefonoC  VARCHAR(10)
);

-- Producto
CREATE TABLE IF NOT EXISTS Producto (
    id_producto  INT PRIMARY KEY AUTO_INCREMENT,
    nombre       VARCHAR(255) NOT NULL,
    descripcion  TEXT,
    id_proveedor INT,
    cantidad     INT     DEFAULT 0,
    precio       INT     DEFAULT 0,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)
);

-- Venta
CREATE TABLE IF NOT EXISTS Venta (
    id_venta     INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente   INT,
    metodo_pago  VARCHAR(50),
    fecha        DATE,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

-- Productos por venta (detalle de factura)
CREATE TABLE IF NOT EXISTS Productos_venta (
    id_productos_venta INT PRIMARY KEY AUTO_INCREMENT,
    id_venta           INT,
    id_producto        INT,
    cantidad           INT,
    precio_unitario    INT,
    FOREIGN KEY (id_venta)    REFERENCES Venta(id_venta),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

-- Factura
CREATE TABLE IF NOT EXISTS Factura (
    id_factura INT PRIMARY KEY AUTO_INCREMENT,
    id_venta   INT,
    total      INT,
    FOREIGN KEY (id_venta) REFERENCES Venta(id_venta)
);

-- Inventario
CREATE TABLE IF NOT EXISTS Inventario (
    id_inventario      INT PRIMARY KEY AUTO_INCREMENT,
    id_producto        INT,
    cantidad           INT,
    fecha_actualizacion DATE,
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);
