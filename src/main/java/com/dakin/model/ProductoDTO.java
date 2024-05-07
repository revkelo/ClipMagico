package com.dakin.model;

public class ProductoDTO {
    // Atributos
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int idProveedor; // Esto podría ser un objeto Proveedor en lugar de un int

    public ProductoDTO() {
		// TODO Auto-generated constructor stub
	}
    // Constructor
    public ProductoDTO(int idProducto, String nombre, String descripcion, int idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
    }
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}


}
