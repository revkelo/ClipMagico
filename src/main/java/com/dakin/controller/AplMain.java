package com.dakin.controller;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;

public class AplMain {
	public static void main(String[] args) {
	    BdSql db = new BdSql();
	    db.MySQLConnect();
//	    db.createTables();
	    
	    ClienteDAO cli = new ClienteDAO(db);
	    cli.agregarCliente("Juan", "Calle 123 123 32", "301340123");
	}
}

