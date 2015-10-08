/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.papamoscas.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author AntonioGamez
 */
@ApplicationPath("api/v3")
public class ApplicationConfig extends Application {

	public static final String PROPERTIES_FILE = "config.properties";
	public static Properties properties = new Properties();

	private Properties readProperties() {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(PROPERTIES_FILE);
		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	@Override
	public Set<Class<?>> getClasses() {
		// Read the properties file
		readProperties();

		// Setup Jersey resources
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	/**
	 * Do not modify addRestResourceClasses() method. It is automatically
	 * populated with all resources defined in the project. If required, comment
	 * out calling this method in getClasses().
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
		if (properties.get("datasource").equals("jsonfile"))
			resources.add(es.us.isa.papamoscas.services.BirdFacadeREST.class);
		else if (properties.get("datasource").equals("cassandra"))
			resources
					.add(es.us.isa.papamoscas.services.BirdFacadeRESTCassandra.class);

	}
}
