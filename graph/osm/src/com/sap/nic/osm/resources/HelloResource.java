package com.sap.nic.osm.resources;
/**
 * @author  steve.hu@sap.com
 * @version created at：May 25, 2015 12:27:42 PM
 * 
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello Jersey";
	}
}