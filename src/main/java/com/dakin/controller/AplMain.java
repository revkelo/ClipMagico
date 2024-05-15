package com.dakin.controller;

import java.util.ArrayList;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;
import com.dakin.model.ClienteDTO;
import com.dakin.model.ProductoDAO;
import com.dakin.model.Productos_ventaDAO;
import com.dakin.model.ProveedorDAO;
import com.dakin.model.VentaDAO;

public class AplMain {
	public static void main(String[] args) {
		BdSql db = new BdSql();
		db.MySQLConnect();
		
		ProductoDAO pro = new ProductoDAO(db);
		ProveedorDAO provee = new ProveedorDAO(db);
		
		Productos_ventaDAO prod = new Productos_ventaDAO(db);
		VentaDAO ven = new VentaDAO(db);
//		prod.agregarProductoVenta(ven.obtenerMaximoIdVenta(), 2, 1);
		
//		
//		ven.agregarVenta(1, 100, "Efectivo");

		

//	    db.createTables();

//		ClienteDAO cli = new ClienteDAO(db);

//		ArrayList<ClienteDTO> list = cli.mostrarClientes();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("" + list.get(i).getNombre());
//		}

	}
}
