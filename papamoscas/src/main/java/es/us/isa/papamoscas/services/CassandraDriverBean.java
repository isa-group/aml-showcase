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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import es.us.isa.papamoscas.entities.Bird;

/**
 *
 * @author isa-tecnico
 */
@Stateless
public class CassandraDriverBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Cluster cluster = Cluster.builder()
			.addContactPoint("10.0.0.5").build();

	// private final Cluster cluster =
	// Cluster.builder().addContactPoint("150.214.188.130").build();

	public void insert(Bird b) {
		try (Session session = cluster.connect("papamoscasKeyspace")) {
			session.execute("INSERT INTO bird (id, specie, place, legDiameter, wingSize, eggs, hatches) VALUES ("
					+ UUID.randomUUID()
					+ ",'"
					+ b.getSpecie()
					+ "','"
					+ b.getPlace()
					+ "',"
					+ b.getLegDiameter()
					+ ","
					+ b.getWingSize()
					+ ","
					+ b.getEggs()
					+ ","
					+ b.getHatches() + ");");
			session.closeAsync();
		}
	}

	public Bird select(UUID id) {
		try (Session session = cluster.connect("papamoscasKeyspace")) {
			ResultSet results = session.execute("SELECT * FROM bird WHERE id="
					+ id);
			if (results != null) {
				Row row = results.all().get(0);
				Bird b = new Bird(row.getUUID("id"), row.getString("specie"),
						row.getString("place"), row.getFloat("legDiameter"),
						row.getFloat("wingSize"), row.getInt("eggs"),
						row.getInt("hatches"));
				return b;
			}
		}
		return null;
	}

	public void update(UUID id, Bird b) {
		try (Session session = cluster.connect("papamoscasKeyspace")) {
			session.execute("UPDATE bird SET specie='" + b.getSpecie()
					+ "', place='" + b.getPlace() + "', legDiameter="
					+ b.getLegDiameter() + ", wingSize=" + b.getWingSize()
					+ ", eggs=" + b.getEggs() + ", hatches=" + b.getHatches()
					+ " WHERE id=" + id);
			session.closeAsync();
		}
	}

	public void delete(UUID id) {
		try (Session session = cluster.connect("papamoscasKeyspace")) {
			session.execute("DELETE from bird WHERE id = " + id);
			session.closeAsync();
		}
	}

	public List<Bird> selectAll() {
		Session session = cluster.connect("papamoscasKeyspace");
		ResultSet results = session.execute("SELECT * FROM bird");
		List<Bird> birdList = new ArrayList<>();
		for (Row row : results.all()) {
			Bird b = new Bird(row.getUUID("id"), row.getString("specie"),
					row.getString("place"), row.getFloat("legDiameter"),
					row.getFloat("wingSize"), row.getInt("eggs"),
					row.getInt("hatches"));
			birdList.add(b);
		}
		session.closeAsync();
		return birdList;
	}

	public String count() {
		Session session = cluster.connect("papamoscasKeyspace");
		ResultSet results = session.execute("SELECT * FROM bird");
		session.closeAsync();
		return Integer.toString(results.all().size());
	}

}
