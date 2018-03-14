package com.lab.sockettest.model;

import java.util.Properties;

import shit.db.ShitDBSession;
import shit.db.ShitDBSessionFactory;
import shit.db.cfg.ShitDBC3P0DataSource;
import shit.db.cfg.ShitDBDataSource;
import shit.db.exception.ShitDBConfigureException;
import shit.db.exception.ShitDBConnectException;
import shit.db.impl.ShitDBSessionJDBCFactory;

public class Dao {
	
	private static ShitDBSession session = null;

	static {
		Properties props = new Properties();
		props.setProperty("driverClass", "com.mysql.jdbc.Driver");
		props.setProperty("jdbcUrl", "jdbc:mysql://111.230.220.211:3306/socket_test?characterEncoding=UTF-8");
		props.setProperty("user", "root");
		props.setProperty("password", "123456");
		ShitDBDataSource dataSource = new ShitDBC3P0DataSource();
		dataSource.setDataSourceByProperties(props);
		dataSource.setShowSql(true);
		ShitDBSessionFactory factory = new ShitDBSessionJDBCFactory();
		factory.setDataSource(dataSource);
		try {
			session = factory.getSession();
		} catch (ShitDBConnectException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
	}
	
	public static ShitDBSession getSession() {
		return session;
	}
	
	
	
}
