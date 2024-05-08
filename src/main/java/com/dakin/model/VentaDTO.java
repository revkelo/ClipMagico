package com.dakin.model;

public class VentaDTO {
	// Atributos
	private int idVenta;
	private int idCliente;
	private int idProducto;
	private int total;
	private int cantidad;
	private String metodo_pago;
	public VentaDTO() {
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public VentaDTO(int idVenta, int idCliente, int idProducto, int total, int cantidad ,String metodo_pago) {
		this.idVenta = idVenta;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.total = total;
		this.cantidad = cantidad;
		this.metodo_pago = metodo_pago;
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

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

}
