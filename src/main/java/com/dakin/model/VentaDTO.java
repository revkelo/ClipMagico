package com.dakin.model;

import java.sql.Date;

public class VentaDTO {
	// Atributos
	private int idVenta;
	private int idCliente;
	private int total;
	private String metodo_pago;
	private Date fecha;
	
	public VentaDTO() {
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public VentaDTO(int idVenta, int idCliente, int total,String metodo_pago ,Date fecha) {
		this.idVenta = idVenta;
		this.idCliente = idCliente;
		this.total = total;	
		this.metodo_pago = metodo_pago;
		this.fecha = fecha;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
