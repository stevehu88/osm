package com.sap.nic.osm.reader;

import java.io.File;
import java.util.HashMap;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

import com.sap.nic.osm.converter.SectionProducer;
import com.sap.nic.osm.converter.NodeConnectionProducer;
import com.sap.nic.osm.model.Section;
import com.sap.nic.osm.model.OSMEntity;
import com.sap.nic.osm.model.OSMGraph;
import com.sap.nic.osm.model.OSMNode;
import com.sap.nic.osm.model.OSMWay;

/**
 * @author steve.hu@sap.com
 * @version created at：May 22, 2015 3:26:48 PM
 * 
 */
public class OSMXMLReader extends XMLReader {

	private static OSMGraph osmgraph;
	
	public OSMXMLReader(String filename) {
		super(filename);
	}

	public OSMXMLReader(File file) {
		super(file);
	}

	private void readAttributes(OSMEntity entity) {
		String temp = parser.getAttributeValue(null, "changeset");
		if (temp != null) {
			entity.setChangeset(Integer.parseInt(temp));
		}
		entity.setId(Long.parseLong(parser.getAttributeValue(null, "id")));
		entity.setTimestamp(parser.getAttributeValue(null, "timestamp"));

		temp = parser.getAttributeValue(null, "uid");
		if (temp != null) {
			entity.setUid(Integer.parseInt(temp));
		}
		entity.setUser(parser.getAttributeValue(null, "user"));

		temp = parser.getAttributeValue(null, "visible");
		if (temp != null) {
			if (temp.equals("true") || temp.equals("1")) {
				entity.setVisible(true);
			}
		} else {
			entity.setVisible(false);
		}
		temp = parser.getAttributeValue(null, "version");
		if (temp != null) {
			entity.setVersion(Integer.parseInt(temp));
		}
	}


	@Override
	public OSMGraph parseXML() {
		String xmlelement;
		OSMNode node = new OSMNode();
		OSMWay way = new OSMWay();
		HashMap<String, String> hashmap = new HashMap<>();
		OSMGraph osm = new OSMGraph();

		try {
			while (parser.hasNext()) {
				switch (parser.getEventType()) {

				case XMLStreamConstants.START_ELEMENT:
					xmlelement = parser.getLocalName();

					if (xmlelement.equals("node")) {
						node = new OSMNode();
						hashmap = new HashMap<>();
						readAttributes(node);
						node.setLatitude(Double.parseDouble(parser
								.getAttributeValue(null, "lat")));
						node.setLongitude(Double.parseDouble(parser
								.getAttributeValue(null, "lon")));
					}

					if (xmlelement.equals("way")) {
						way = new OSMWay();
						hashmap = new HashMap<>();
						readAttributes(way);
					}

					
					if (xmlelement.equals("bounds")) {
						osm.setBbbottom(Double.parseDouble(parser
								.getAttributeValue(null, "minlat")));
						osm.setBbtop(Double.parseDouble(parser
								.getAttributeValue(null, "maxlat")));
						osm.setBbleft(Double.parseDouble(parser
								.getAttributeValue(null, "minlon")));
						osm.setBbright(Double.parseDouble(parser
								.getAttributeValue(null, "maxlon")));
					}

					if (xmlelement.equals("nd")) {
						way.addNode(osm.getNode(Long.parseLong(parser
								.getAttributeValue(0))));
					}

					if (xmlelement.equals("tag")) {
						hashmap.put(parser.getAttributeValue(null, "k"),
								parser.getAttributeValue(null, "v"));
//						System.out.println(parser.getAttributeValue(null, "k") + "  " + parser.getAttributeValue(null, "v"));
					}

					break;
				case XMLStreamConstants.END_ELEMENT:
					if (parser.getLocalName() == "node") {
						node.setHashmap(hashmap);
						osm.addNode(node);
					}

					if (parser.getLocalName() == "way") {
						way.setHashmap(hashmap);
//						String highway_value = way.getValue("highway");
						osm.addWay(way);
/**
						if (highway_value != null)
						{
							if (highway_value.equals("motorway") || highway_value.equals("motorway_link")
									|| highway_value.equals("trunk")   || highway_value.equals("trunk_link")
									|| highway_value.equals("primary")    || highway_value.equals("primary_link")
									|| highway_value.equals("secondary")    || highway_value.equals("secondary_link")
									|| highway_value.equals("tertiary")    || highway_value.equals("tertiary_link")						
									){	
									osmgraph.addWay(way);	
								}
						}
**/

					}

					break;

				}
				parser.next();
			}

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	
		NodeConnectionProducer producer = new NodeConnectionProducer(osm);
		producer.produceNodesConnections();
	
		SectionProducer sectionProducer = new SectionProducer(osm);
		sectionProducer.buildSectionGraph();

		return osm;
	}

    public OSMGraph getOSMGraph() {
    	if (osmgraph == null) {
    		osmgraph = this.parseXML();
    	}
        return osmgraph;
    }
    
    
    public static void main(String[] args) {
    	OSMXMLReader reader = new OSMXMLReader("nanjing.osm");
    	OSMGraph osmgraph = reader.getOSMGraph();
    	for(Section section : osmgraph.getSections()){
        		System.out.println(section.getNewid() + " " + section.getWayType());	
    	}

    }
}
