package com.dakin.model;

public class ProveedorDTO {
    // Atributos
    private int idProveedor;
    private String nombre;
    private String direccion;
    private String NIT;
    private String telefonoP;

    public ProveedorDTO() {
		// TODO Auto-generated constructor stub
	}
    // Constructor
    public ProveedorDTO(int idProveedor, String nombre, String direccion, String NIT, String telefonoP) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.NIT = NIT;
        this.telefonoP = telefonoP;
    }
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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
	public String getNIT() {
		return NIT;
	}
	public void setNIT(String nIT) {
		NIT = nIT;
	}
	public String getTelefonoP() {
		return telefonoP;
	}
	public void setTelefonoP(String telefonoP) {
		this.telefonoP = telefonoP;
	}


}
