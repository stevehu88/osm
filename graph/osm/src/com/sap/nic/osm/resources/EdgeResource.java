package com.sap.nic.osm.resources;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sap.nic.osm.model.Section;
import com.sap.nic.osm.model.OSMGraph;
import com.sap.nic.osm.reader.OSMXMLReader;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 25, 2015 1:05:11 PM
 * 
 */

@Path("Edge")
public class EdgeResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response queryEdges() {
		String path = this.getClass().getResource("/nanjing.osm").getPath();
		OSMXMLReader reader = new OSMXMLReader(path);
    	OSMGraph osmgraph = reader.getOSMGraph();
    	ArrayList<Section> list = osmgraph.getSections();
    	GenericEntity entity = new GenericEntity<List<Section>>(list) {};
    	return Response.ok(entity).build();
	}
}
