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

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ejb.EJB;
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

import es.us.isa.papamoscas.entities.AbstractFacade;
import es.us.isa.papamoscas.entities.Bird;

/**
 *
 * @author AntonioGamez
 */
@Stateless
@Path("birds")
public class BirdFacadeRESTCassandra extends AbstractFacade<Bird> {

    //@PersistenceContext(unitName = "ds_pu3", type = PersistenceContextType.EXTENDED)
    //private EntityManager em;
    @EJB
    CassandraDriverBean cd;

    public BirdFacadeRESTCassandra() {
        super(Bird.class);
    }

    /* POST */
    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Bird entity) {
        cd.insert(entity);
    }

    /* PUT */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") UUID id, Bird entity) {
        cd.update(id, entity);
    }

    /* DELETE */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") UUID id) {
        cd.delete(id);
    }

    /* GET */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bird find(@PathParam("id") UUID id) {
        return cd.select(id);
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bird> findAll() {
        return cd.selectAll();
    }

    /* Other */
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bird> findRange(@PathParam("from") UUID from, @PathParam("to") UUID to) {
        return null;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return cd.count();
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
        return null;//em;
    }

}
