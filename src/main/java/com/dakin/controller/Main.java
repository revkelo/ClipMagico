package com.dakin.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.dakin.model.BdSql;
import com.dakin.model.ClienteDAO;

public class Main {
	public static void main(String[] args) {

		BdSql sql = new BdSql();
		sql.MySQLConnect();

		ClienteDAO cli = new ClienteDAO();

		cli.agregarCliente(0, "Juan", "Calle 155", "30139");

		cli.mostrarCliente(0);
	}
}
