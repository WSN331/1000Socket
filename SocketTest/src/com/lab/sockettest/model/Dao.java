package com.lab.sockettest.model;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import shit.db.ShitDBSession;
import shit.db.ShitDBSessionFactory;
import shit.db.cfg.ShitDBC3P0DataSource;
import shit.db.cfg.ShitDBDataSource;
import shit.db.exception.ShitDBConfigureException;
import shit.db.exception.ShitDBConnectException;
import shit.db.impl.ShitDBSessionJDBCFactory;

public class Dao implements ServletContextListener {
	
	private static ShitDBSession session = null;
	
	private static Properties props = new Properties();
	
	 /**
     * 读取配置文件
     * @param fileName
     */
    private static void readProperties(String fileName){
        try {
            InputStream in = Dao.class.getResourceAsStream("/"+fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            props.load(bf);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    
	public final static void init(){
		readProperties("jdbc.properties");
		ShitDBDataSource dataSource = new ShitDBC3P0DataSource();
		dataSource.setDataSourceByProperties(props);
		dataSource.setShowSql(false);
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


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}


	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		init();
		
	}
	
	
	
}
