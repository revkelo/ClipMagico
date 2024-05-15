package com.dakin.model;

import java.sql.Date;

public class Productos_ventaDTO {
	// Atributos
	private int idVenta;
	private int idProducto;
	private int cantidad;

	public Productos_ventaDTO() {
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public Productos_ventaDTO(int idVenta, int idProducto, int cantidad) {
		this.idVenta = idVenta;
		this.idProducto = idProducto;
		this.cantidad = cantidad;

	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
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


	
}
