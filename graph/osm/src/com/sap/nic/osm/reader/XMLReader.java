package com.sap.nic.osm.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 22, 2015 3:17:42 PM
 * 
 */
public abstract class XMLReader {
	protected String filename;
	protected XMLInputFactory factory = XMLInputFactory.newFactory();
	protected XMLStreamReader parser;
	
	public XMLReader(String filename) {
		try {
			parser = factory.createXMLStreamReader(new FileInputStream(filename));
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public XMLReader(File file){
		try {
			parser = factory.createXMLStreamReader(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void parseXML();
	
}
