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
public class OSMEdge {
	
	private OSMNode fromNode;
	private OSMNode toNode;
	private Vector<OSMNode> nodes;
	
	private ArrayList<OSMWay> ways = new ArrayList<OSMWay>();
	
	
	private long newid;
	
	private long backEdgeId;
	
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
	
	public void addWay(OSMWay way) {
		this.ways.add(way);
	}

	public long getNewid() {
		return newid;
	}

	public void setNewid(long newid) {
		this.newid = newid;
	}

	public long getBackEdgeId() {
		return backEdgeId;
	}

	public void setBackEdgeId(long backEdgeId) {
		this.backEdgeId = backEdgeId;
	}
}
