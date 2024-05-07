package com.dakin.model;

import java.util.Date;

public class InventarioDTO {
    // Atributos
    private int idInventario;
    private int idProducto;
    private int cantidad;
    private Date fechaActualizacion;

    public InventarioDTO() {
		// TODO Auto-generated constructor stub
	}
    // Constructor
    public InventarioDTO(int idInventario, int idProducto, int cantidad, Date fechaActualizacion) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fechaActualizacion = fechaActualizacion;
    }
	public int getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

    
}
