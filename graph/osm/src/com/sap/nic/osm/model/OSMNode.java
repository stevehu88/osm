package com.sap.nic.osm.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 22, 2015 3:03:43 PM
 * 
 */
@XmlRootElement
public class OSMNode extends OSMEntity{
	   private double latitude;
	    private double longitude;
	    private ArrayList<OSMNode> succNodeList = new ArrayList<>();
	    private ArrayList<OSMNode> predNodeList = new ArrayList<>();
	    private ArrayList<OSMEdge> outgoingEdge = new ArrayList<>();
	    
	    
	    public double getLatitude() {
	        return latitude;
	    }
	    public void setLatitude(double latitude) {
	        this.latitude = latitude;
	    }
	    public double getLongitude() {
	        return longitude;
	    }
	    public void setLongitude(double longitude) {
	        this.longitude = longitude;
	    }
	    
	    public String toString(){
	        return valuestoString() + "Lat: " + latitude + "\n" + "Lon: " + longitude;
	    }

	    public ArrayList<OSMNode> getSuccNodeList() {
	        return succNodeList;
	    }

	    public ArrayList<OSMNode> getPredNodeList() {
	        return predNodeList;
	    }
	    
	    public void addSuccNode(OSMNode node) {
	        succNodeList.add(node);
	    }
	    
	    public void addPredNode(OSMNode node) {
	        predNodeList.add(node);
	    }
	    
	    
		public ArrayList<OSMEdge> getOutgoingEdge() {
			return outgoingEdge;
		}
		
		public void addOutgoingEdge(OSMEdge edge) {
			this.outgoingEdge.add(edge);
		}
	    
	    
}
