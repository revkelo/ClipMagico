# ClipMagico

Sistema de **punto de venta (POS)** desarrollado en Java con Jakarta Servlet + MySQL. Permite gestionar clientes, proveedores, productos, ventas y facturas desde un panel de administrador y un panel de vendedor con interfaz web.

---

## Funcionalidades

### Panel Administrador
- Registrar, listar y eliminar **clientes**
- Registrar, listar y eliminar **proveedores**
- Registrar, listar y eliminar **productos** (con precio y cantidad en inventario)
- Registrar **ventas** con método de pago y fecha
- Ver detalle de **productos por venta**
- Consultar **facturas**

### Panel Vendedor
- Registrar ventas
- Consultar productos disponibles
- Ver historial de ventas propias

### Landing page
- Página de presentación del sistema con carousel y sección de servicios

---

## Stack

| Capa | Tecnología |
|------|-----------|
| Backend | Java 17 · Jakarta Servlet 6.0 |
| Base de datos | MySQL 8 · JDBC (mysql-connector-j 8.3) |
| Serialización JSON | Gson 2.8.8 |
| Frontend | HTML5 · Bootstrap 5 · jQuery · Font Awesome |
| Build | Maven (WAR) |
| Servidor | Apache Tomcat 10+ |

---

## Modelo de datos

```
Proveedor ──< Producto >──< Productos_venta >── Venta ──> Cliente
                │                                  │
             Inventario                         Factura
```

| Tabla | Campos clave |
|-------|-------------|
| `Cliente` | id, nombre, direccion, telefonoC |
| `Proveedor` | id, nombre, NIT, telefonoP |
| `Producto` | id, nombre, descripcion, precio, cantidad, id_proveedor |
| `Venta` | id, id_cliente, metodo_pago, fecha |
| `Productos_venta` | id, id_venta, id_producto, cantidad, precio_unitario |
| `Factura` | id, id_venta, total |
| `Inventario` | id, id_producto, cantidad, fecha_actualizacion |

---

## Servlets

| Servlet | Responsabilidad |
|---------|----------------|
| `ServletAdmin` | Panel de administrador |
| `ServletVendedor` | Panel de vendedor |
| `ServletCliente` | CRUD de clientes |
| `ServletProducto` | CRUD de productos |
| `ServletProveedor` | CRUD de proveedores |
| `ServletVenta` | Registro y consulta de ventas |
| `ServletListar` | Listado general de entidades |

---

## Instalación

**Requisitos:** Java 17 · Maven · MySQL 8 · Apache Tomcat 10+

### 1. Clonar y compilar

```bash
git clone https://github.com/revkelo/ClipMagico.git
cd ClipMagico
mvn clean package
```

### 2. Configurar la base de datos

```bash
mysql -u root -p < src/main/resources/schema.sql
```

### 3. Variables de entorno

Configurar antes de arrancar Tomcat:

```bash
export DB_URL=jdbc:mysql://localhost:3306/clipmagico
export DB_USERNAME=root
export DB_PASSWORD=tu_password
```

### 4. Desplegar

Copiar el `.war` generado en `target/` al directorio `webapps/` de Tomcat y arrancar el servidor.

```
http://localhost:8080/ClipMagico/
```

---

## Estructura

```
src/main/
├── java/com/dakin/
│   ├── controller/
│   │   ├── AplMain.java
│   │   ├── ServletAdmin.java
│   │   ├── ServletCliente.java
│   │   ├── ServletListar.java
│   │   ├── ServletProducto.java
│   │   ├── ServletProveedor.java
│   │   ├── ServletVendedor.java
│   │   └── ServletVenta.java
│   └── model/
│       ├── BdSql.java               ← conexión MySQL (lee vars de entorno)
│       ├── ClienteDAO/DTO.java
│       ├── ProductoDAO/DTO.java
│       ├── ProveedorDAO/DTO.java
│       ├── VentaDAO/DTO.java
│       ├── FacturaDAO/DTO.java
│       └── Productos_ventaDAO/DTO.java
└── webapp/
    ├── index.html                   ← landing page
    ├── administrador.html           ← panel admin
    ├── vendedor.html                ← panel vendedor
    └── css/ · js/
```

---

Proyecto grupal — Universidad El Bosque, 2024  
**Kevin Gonzalez** · Daniela · Nicolás · Andrés

