/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.papamoscas.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import es.us.isa.aml.util.Util;
import es.us.isa.papamoscas.entities.AbstractFacade;
import es.us.isa.papamoscas.entities.Bird;

/**
 * @author jdelafuente
 *
 */
@Stateless
@Path("birds")
public class BirdFacadeREST extends AbstractFacade<Bird> {

	private final static String JSONFILENAME = "/birds.json";

	public BirdFacadeREST() {
		super(Bird.class);
	}

	/* POST */
	@POST
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Bird entity) {
		try {
			FileWriter fw = new FileWriter(BirdFacadeREST.class.getResource(
					JSONFILENAME).toString(), true);
			fw.write(new Gson().toJson(entity));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* PUT */
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void edit(Bird entity) {

	}

	/* DELETE */
	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") UUID id) {

	}

	/* GET */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bird find(@PathParam("id") UUID id) {
		for (Bird b : findAll()) {
			if (b.getId().equals(id))
				return b;
		}
		return null;
	}

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bird> findAll() {
		TypeToken<List<Bird>> token = new TypeToken<List<Bird>>() {
			private static final long serialVersionUID = 1L;
		};
		List<Bird> birds = new Gson().fromJson(Util
				.getStringFromInputStream(BirdFacadeREST.class
						.getResourceAsStream(JSONFILENAME)), token.getType());
		return birds;
	}

	/* Other */
	@GET
	@Path("{from}/{to}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bird> findRange(@PathParam("from") UUID from,
			@PathParam("to") UUID to) {
		return null;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String countREST() {
		return String.valueOf(findAll().size());
	}

	/* Analytics */
	@GET
	@Path("analytics/slow")
	@Produces(MediaType.TEXT_PLAIN)
	public String slow() throws InterruptedException {
		Thread.sleep(2000);
		return "OK-slow: " + Math.abs(new Random().nextGaussian() * 5);
	}

	@GET
	@Path("analytics/fast")
	@Produces(MediaType.TEXT_PLAIN)
	public String fast() {
		return "OK-fast: " + Math.abs(new Random().nextGaussian() * 5);
	}

	@Override
	protected EntityManager getEntityManager() {
		return null;// em;
	}

}
