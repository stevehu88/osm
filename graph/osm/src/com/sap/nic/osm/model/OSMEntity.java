package com.sap.nic.osm.model;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 22, 2015 2:56:19 PM
 * 
 */
@XmlRootElement
public class OSMEntity {
	
	private long id;
	private boolean visible;
	private String timestamp;
	private String user;
	private int uid;
	private int changeset;
	private HashMap<String, String> hashmap = new HashMap<>();
	private int version;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getChangeset() {
		return changeset;
	}
	public void setChangeset(int changeset) {
		this.changeset = changeset;
	}
	public HashMap<String, String> getHashmap() {
		return hashmap;
	}
	public void setHashmap(HashMap<String, String> hashmap) {
		this.hashmap = hashmap;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	
	public void setKeyValue(String key, String value){
		hashmap.put(key, value);
	}
	
	public String getValue(String key){
		return hashmap.get(key);
	}
	
	public boolean hasKey(String key){
		return hashmap.containsKey(key);
	}
	
	public boolean hasValue(String value){
		return hashmap.containsValue(value);
	}
	
	protected String valuestoString(){
		 return ("ID: " + (id) + ";  " + "User: " + user + ";  ");
	}
	
	
	
}
