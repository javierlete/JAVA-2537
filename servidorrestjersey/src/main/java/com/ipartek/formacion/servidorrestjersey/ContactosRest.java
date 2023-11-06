package com.ipartek.formacion.servidorrestjersey;

import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeMap;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("contactos")
public class ContactosRest {

	private static final TreeMap<Long, Contacto> contactos = new TreeMap<>();

	static {
		contactos.put(1L, new Contacto(1L, "Uno", "Unez", LocalDate.now()));
		contactos.put(2L, new Contacto(2L, "Dos", "Dosez", LocalDate.now()));
	}
	
    @GET
    public Collection<Contacto> getContactos() {
        return contactos.values(); 
    }
    
    @GET
    @Path("{id}")
    public Response getContacto(@PathParam("id") Long id) {
    	Contacto c = contactos.get(id);
    	
    	if(c == null) {
    		return Response.status(Status.NOT_FOUND).build();
    	} 
    	
    	return Response.status(Status.OK).entity(c).build();
    }
    
    @POST
    public Response postContacto(Contacto contacto) {
    	contactos.put(contacto.getId(), contacto);
    	return Response.status(Status.CREATED).entity(contacto).build();
    }
    
    @PUT
    @Path("{id}")
    public Contacto putContacto(@PathParam("id") Long id, Contacto contacto) {
    	contactos.put(id, contacto);
    	return contacto;
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteContacto(@PathParam("id") Long id) {
    	contactos.remove(id);
    	
    	return Response.status(Status.NO_CONTENT).build();
    }
}
