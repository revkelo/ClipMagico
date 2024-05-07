package com.dakin.model;

public class ClienteDTO {
    // Atributos
    private int idCliente;
    private String nombre;
    private String direccion;
    private String telefonoC;

    
    public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
    // Constructor
    public ClienteDTO(int idCliente, String nombre, String direccion, String telefonoC) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonoC = telefonoC;
    }
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefonoC() {
		return telefonoC;
	}
	public void setTelefonoC(String telefonoC) {
		this.telefonoC = telefonoC;
	}

    
}
