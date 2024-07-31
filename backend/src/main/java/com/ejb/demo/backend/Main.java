package com.ejb.demo.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.openejb.OpenEjbContainer;

import jakarta.ejb.embeddable.EJBContainer;

public class Main {
	private static Context context;
	private static CustomerService service;
	private static List<Customer> customers = new ArrayList<>();

	public static void main(String[] args) throws NamingException, InterruptedException {

		Properties properties = new Properties();
		properties.setProperty(OpenEjbContainer.OPENEJB_EMBEDDED_REMOTABLE, "true");
		context = EJBContainer.createEJBContainer(properties).getContext();

		// create some records
		service = (CustomerService) context.lookup("java:global/rest-on-ejb/CustomerService");
		customers.add(service.create("John", "Doe"));
		customers.add(service.create("Robert", "Luna"));
		System.out.println("*******************************************************************");
		Thread.sleep(100000000);
	}

}
