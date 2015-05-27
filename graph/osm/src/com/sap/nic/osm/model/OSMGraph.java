package com.sap.nic.osm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 22, 2015 3:44:26 PM
 * 
 */
@XmlRootElement
public class OSMGraph extends Graph {
    private final HashMap<Long, OSMWay> ways = new HashMap<>();
    private HashMap<Long, OSMRelation> relations = new HashMap<>();
    private final HashMap<Long, OSMNode> nodes = new HashMap<>();
    
    private final ArrayList<Section> edges = new ArrayList<>();
    
    public OSMNode getNode(long id) {
        return nodes.get(id);
    }

    public void removeWay(OSMWay way) {
        ways.remove(way);
    }

    public OSMNode[] getNodes(){
        OSMNode[] nodearray= new OSMNode[nodes.size()];
        return nodes.values().toArray(nodearray);
    }


    public void addNode(OSMNode node) {
        nodes.put(node.getId(), node);
    }
    
    public void addWay(OSMWay way) {
        ways.put(way.getId(), way);
    }

    public OSMWay getWay(long id) {
        return ways.get(id);
    }

    public OSMRelation getRelation(long id) {
        return relations.get(id);
    }

    public  HashMap<Long, OSMRelation> getRelationsAshashmap() {
        return relations;
    }

    public void setRelations( HashMap<Long, OSMRelation> relations) {
        this.relations = relations;
    }

    public OSMWay[] getWays() {
        OSMWay[] wayarray= new OSMWay[ways.size()];
        return ways.values().toArray(wayarray);
    }

    public void addRelation(OSMRelation relation) {
        relations.put(relation.getId(), relation);
    }

    public OSMRelation[] getRelations(){
        OSMRelation[] relationarray = new OSMRelation[relations.size()];
        return relations.values().toArray(relationarray);
    }

    public Collection<OSMRelation> getRelationsCollection() {
        return relations.values();
    }

    public boolean hasNode(Long id) {
        return nodes.containsKey(id);
    }

    public ArrayList<Long> getIDsfromWay(int id) {
        OSMWay w = ways.get(id);
        ArrayList<Long> ids  = new ArrayList<>();
        ids.add(w.getToNode().getId());
        ids.add(w.getFromNode().getId());
        return ids;
    }
    
    public void addEdge(Section edge) {
    	this.edges.add(edge);
    }

	public ArrayList<Section> getEdges() {
		return edges;
	}

    
    

    
}
