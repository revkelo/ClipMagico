package com.dakin.model;

public class VentaDTO {
	// Atributos
	private int idVenta;
	private int idCliente;
	private int idProducto;
	private int total;
	private int cantidad;

	public VentaDTO() {
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public VentaDTO(int idVenta, int idCliente, int idProducto, int total, int cantidad) {
		this.idVenta = idVenta;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.total = total;
		this.cantidad = cantidad;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
