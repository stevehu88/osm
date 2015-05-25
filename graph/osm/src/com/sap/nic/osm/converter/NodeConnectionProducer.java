package com.sap.nic.osm.converter;

import com.sap.nic.osm.model.OSMGraph;
import com.sap.nic.osm.model.OSMNode;
import com.sap.nic.osm.model.OSMWay;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 25, 2015 9:37:09 AM
 * 
 */
public class NodeConnectionProducer {
	
	private OSMGraph osmgraph;
	private OSMWay[] osmways;
	
	public NodeConnectionProducer(OSMGraph osmgraph) {
		this.osmgraph = osmgraph;
		osmways = this.osmgraph.getWays();
	}
	
	public void produceNodesConnections() {
		OSMNode[] waynodes;
		for (int i = 0; i < osmways.length; i ++)
		{
			waynodes = osmways[i].getNodes();
			for (int j = 0; j < waynodes.length - 1; j ++) {
				waynodes[j].addSuccNode(waynodes[j + 1]);
				waynodes[j+1].addPredNode(waynodes[j]);
			}
		}
	}
}
