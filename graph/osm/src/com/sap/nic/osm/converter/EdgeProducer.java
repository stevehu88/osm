package com.sap.nic.osm.converter;

import java.util.Vector;

import com.sap.nic.osm.model.OSMEdge;
import com.sap.nic.osm.model.OSMGraph;
import com.sap.nic.osm.model.OSMNode;
import com.sap.nic.osm.model.OSMWay;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 25, 2015 9:55:38 AM
 * 
 */
public class EdgeProducer {
	
	
	private OSMGraph osmgraph;
	
	public EdgeProducer(OSMGraph osmgraph) {
		this.osmgraph = osmgraph;
	}
	
	
	private OSMEdge newEdge = new OSMEdge();
	private long newid = 1;
	
	private void setNewEdgeID(OSMEdge edge) {
		edge.setNewid(newid);
		newid ++;
	}
	
	
	private OSMEdge createEdge(OSMNode fromNode, int fromNodeIndex, OSMNode toNode, int toNodeIndex, OSMWay way) {
		newEdge = new OSMEdge();
		newEdge.setFromNode(fromNode);
		newEdge.setToNode(toNode);
		setNewEdgeID(newEdge);
		
		osmgraph.addEdge(newEdge);
		fromNode.addOutgoingEdge(newEdge);
		newEdge.addWay(way);
		
		Vector<OSMNode> nodes = new Vector<>();
		if (fromNodeIndex < toNodeIndex) {
			for(int i = fromNodeIndex; i <= toNodeIndex; i ++) {
				nodes.add(way.getNodes()[i]);
			}
			newEdge.setNodes(nodes);
		}
		else
		{
			for(int i = fromNodeIndex; i >= toNodeIndex; i --) {
				nodes.add(way.getNodes()[i]);
			}
			newEdge.setNodes(nodes);
		}

		
		return newEdge;
	}
	
	
	OSMEdge backEdge;
	private void createBackEdge(OSMNode fromNode, int fromNodeIndex, OSMNode toNode, int toNodeIndex, OSMWay way, OSMEdge edge) {
		backEdge = new OSMEdge();
		backEdge = createEdge(fromNode, fromNodeIndex,toNode, toNodeIndex,way);
		
		backEdge.setBackEdgeId(edge.getNewid());
		edge.setBackEdgeId(backEdge.getNewid());
		
	}
	
	
	private void buildEdges(OSMWay way, boolean oneway) {
		OSMEdge tempEdge = new OSMEdge();
		
		OSMNode lastUsedNode = way.getFromNode();
		int lastUsedNodeIndex = 0;
		OSMNode[] nodes = way.getNodes();
		
		for (int i = 1; i < nodes.length; i ++) {
			// 
			if(nodes[i].getPredNodeList().size() > 1 || nodes[i].getSuccNodeList().size() > 1) {
				tempEdge = createEdge(lastUsedNode, lastUsedNodeIndex, nodes[i], i, way);
				
				if (oneway == false) {
					createBackEdge(nodes[i], i, lastUsedNode, lastUsedNodeIndex, way, tempEdge);
				}
				lastUsedNode = nodes[i];
				lastUsedNodeIndex = i;
			}
			else if (nodes[i].getSuccNodeList().size() == 0) {
				tempEdge = createEdge(lastUsedNode, lastUsedNodeIndex, nodes[i], i, way);
				
				if (oneway == false) {
					createBackEdge(nodes[i], i, lastUsedNode, lastUsedNodeIndex, way, tempEdge);
				}
			}
			else if (i == nodes.length - 1 && nodes[i].getSuccNodeList().size() == 1) {
				tempEdge = createEdge(lastUsedNode, lastUsedNodeIndex, nodes[i], i, way);
				
				if(oneway = false) {
					createBackEdge(nodes[i], i, lastUsedNode, lastUsedNodeIndex, way, tempEdge);
				}
				lastUsedNode = nodes[i];
				lastUsedNodeIndex = i;
			}
		}
		
	}
	
	
	public void buildEdgeGraph() {
		OSMWay[] osmways = this.osmgraph.getWays();
		for (int i = 0; i < osmways.length; i ++) {
			buildEdges( osmways[i], osmways[i].isOneWay());
		}
	}
}
