package com.sap.nic.osm.converter;

import java.util.Vector;

import com.sap.nic.osm.model.Section;
import com.sap.nic.osm.model.OSMGraph;
import com.sap.nic.osm.model.OSMNode;
import com.sap.nic.osm.model.OSMWay;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 25, 2015 9:55:38 AM
 * 
 */
public class SectionProducer {
	
	
	private OSMGraph osmgraph;
	
	public SectionProducer(OSMGraph osmgraph) {
		this.osmgraph = osmgraph;
	}
	
	
	private Section newSection = new Section();
	private long newid = 1;
	
	private void setNewSectionID(Section section) {
		section.setNewid(newid);
		newid ++;
	}
	
	
	private Section createSection(OSMNode fromNode, int fromNodeIndex, OSMNode toNode, int toNodeIndex, OSMWay way) {
		newSection = new Section();
		newSection.setFromNode(fromNode);
		newSection.setToNode(toNode);
		setNewSectionID(newSection);
		
		osmgraph.addSection(newSection);
		fromNode.addOutgoingEdge(newSection);
		newSection.addWay(way);
		
		Vector<OSMNode> nodes = new Vector<>();
		if (fromNodeIndex < toNodeIndex) {
			for(int i = fromNodeIndex; i <= toNodeIndex; i ++) {
				nodes.add(way.getNodes()[i]);
			}
			newSection.setNodes(nodes);
		}
		else
		{
			for(int i = fromNodeIndex; i >= toNodeIndex; i --) {
				nodes.add(way.getNodes()[i]);
			}
			newSection.setNodes(nodes);
		}
		
		newSection.setWayType(way.getValue("highway"));
		
		return newSection;
	}
	
	
	Section backSection;
	private void createBackSection(OSMNode fromNode, int fromNodeIndex, OSMNode toNode, int toNodeIndex, OSMWay way, Section section) {
		backSection = new Section();
		backSection = createSection(fromNode, fromNodeIndex,toNode, toNodeIndex,way);
		
		backSection.setBackSectionId(section.getNewid());
		section.setBackSectionId(backSection.getNewid());
		
	}
	
	
	private void buildSections(OSMWay way, boolean oneway) {
		Section tempSection = new Section();
		
		OSMNode lastUsedNode = way.getFromNode();
		int lastUsedNodeIndex = 0;
		OSMNode[] nodes = way.getNodes();
		
		for (int i = 1; i < nodes.length; i ++) {
			// 
			if(nodes[i].getPredNodeList().size() > 1 || nodes[i].getSuccNodeList().size() > 1) {
				tempSection = createSection(lastUsedNode, lastUsedNodeIndex, nodes[i], i, way);
				
				if (oneway == false) {
					createBackSection(nodes[i], i, lastUsedNode, lastUsedNodeIndex, way, tempSection);
				}
				lastUsedNode = nodes[i];
				lastUsedNodeIndex = i;
			}
			else if (nodes[i].getSuccNodeList().size() == 0) {
				tempSection = createSection(lastUsedNode, lastUsedNodeIndex, nodes[i], i, way);
				
				if (oneway == false) {
					createBackSection(nodes[i], i, lastUsedNode, lastUsedNodeIndex, way, tempSection);
				}
			}
			else if (i == nodes.length - 1 && nodes[i].getSuccNodeList().size() == 1) {
				tempSection = createSection(lastUsedNode, lastUsedNodeIndex, nodes[i], i, way);
				
				if(oneway = false) {
					createBackSection(nodes[i], i, lastUsedNode, lastUsedNodeIndex, way, tempSection);
				}
				lastUsedNode = nodes[i];
				lastUsedNodeIndex = i;
			}
		}
		
	}
	
	
	public void buildSectionGraph() {
		OSMWay[] osmways = this.osmgraph.getWays();
		for (int i = 0; i < osmways.length; i ++) {
			buildSections( osmways[i], osmways[i].isOneWay());
		}
	}
}
