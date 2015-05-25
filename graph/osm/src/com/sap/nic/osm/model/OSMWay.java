package com.sap.nic.osm.model;

import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 22, 2015 3:11:06 PM
 * 
 */
@XmlRootElement
public class OSMWay extends OSMEntity {
	private Vector<OSMNode> nodes = new Vector<>();
	
	public OSMNode[] getNodes(){
		OSMNode[] nodearray = new OSMNode[nodes.size()];
		return (OSMNode[]) nodes.toArray(nodearray);
	}
	
	public OSMNode getToNode() {
		return nodes.lastElement();
	}
	
	public OSMNode getFromNode() {
		return nodes.firstElement();
	}
	
	public void addNode(OSMNode node) {
		nodes.add(node);
	}
	
    public String tosString() {
        System.out.println(this.getId());
        return this.valuestoString() + "fromNodeID: "
                + this.getFromNode().getId() + "\ntoNodeID: "
                + this.getToNode().getId();
    }
    
    public boolean isOneWay() {
    	if (this.hasKey("oneway")) {
    		String t = this.getValue("oneway");
    		if (t.equals("1") || t.equals("true") || t.equals("yes")) {
    			return true;
    		}
    	}
    	String t = this.getValue("highway");
    	if (t != null) {
    		if (t.equals("motorway") || t.equals("motorway_link")) {
    			return true;
    		}
    		else {
    			return false;
    		}
    		
    	}
    	return false;
    }
	
}
