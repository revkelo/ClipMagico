package com.dakin.model;

import java.sql.Date;

public class FacturaDTO {
	// Atributos
	private int idFactura;
	private int idVenta;
	private int total;

	
	public FacturaDTO() {
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public FacturaDTO(int idFactura,int idVenta, int total) {
		this.idVenta = idVenta;
		this.idFactura = idFactura;
		this.total = total;	

	}
	
	
	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	
}
