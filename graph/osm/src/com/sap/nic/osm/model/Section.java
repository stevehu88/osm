package com.sap.nic.osm.model;

import java.util.ArrayList;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 25, 2015 9:45:23 AM
 * 
 */
@XmlRootElement
public class Section {
	
	private OSMNode fromNode;
	private OSMNode toNode;
	private Vector<OSMNode> nodes;
	
	private ArrayList<OSMWay> ways = new ArrayList<OSMWay>();
	
	
	private long newid;
	
	private long backSectionId;
	
	private String wayType;
	
	public OSMNode getFromNode() {
		return fromNode;
	}
	
	public void setFromNode(OSMNode fromNode) {
		this.fromNode = fromNode;
	}
	public OSMNode getToNode() {
		return toNode;
	}
	public void setToNode(OSMNode toNode) {
		this.toNode = toNode;
	}
	public Vector<OSMNode> getNodes() {
		return nodes;
	}
	public void setNodes(Vector<OSMNode> nodes) {
		this.nodes = nodes;
	}
	
	public ArrayList<OSMWay> getWays() {
		return ways;
	}

	public void setWays(ArrayList<OSMWay> ways) {
		this.ways = ways;
	}

	public void addWay(OSMWay way) {
		this.ways.add(way);
	}

	public long getNewid() {
		return newid;
	}

	public void setNewid(long newid) {
		this.newid = newid;
	}

	public long getBackSectionId() {
		return backSectionId;
	}

	public void setBackSectionId(long backEdgeId) {
		this.backSectionId = backEdgeId;
	}
	
	public void setWayType(String wayType) {
		this.wayType = wayType;
	}

	public String getWayType() {
		return this.wayType;
	}
}
