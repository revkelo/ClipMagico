package com.dakin.controller;

import com.dakin.model.BdSql;

public class AplMain {
	public static void main(String[] args) {
	    BdSql db = new BdSql();
	    db.MySQLConnect();
	    db.createTables();
	}
}

