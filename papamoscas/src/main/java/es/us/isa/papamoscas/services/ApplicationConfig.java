/*******************************************************************************
 * AML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AML. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) ISA Research Group - University of Sevilla, 2015
 * Licensed under GPL (https://github.com/isa-group/aml/blob/master/LICENSE.txt)
 *******************************************************************************/
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
