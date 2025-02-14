package vub.templates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mt4j.MTAndroidApplication;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.content.Context;
import android.os.Environment;

import vub.ast.Node;
import vub.tiamat.StartTiamat;

public class TemplateWriter{

	public void write() {
		try {
			//StartTiamat.reOpen();
			//String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <templates>" + vub.tiamat.Tiamat.main.toXML(rootElement)+ "</templates>";
			//System.out.println("TemplateWriter"+ XML);
			//StartTiamat.out.write("XML:" + XML+ "yr");
			StartTiamat.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	public void write2(){
	 
		  try {
	 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("templates");
			doc.appendChild(rootElement);
			
			Element template = doc.createElement("template");
			rootElement.appendChild(template);
	 
			vub.tiamat.Tiamat.main.toXML(template, doc);
			
			/*
			// staff elements 
			Element staff = doc.createElement("Staff");
			rootElement.appendChild(staff);
	 
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);
	 
			// shorten way
			// staff.setAttribute("id", "1");
	 
			// firstname elements
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode("yong"));
			staff.appendChild(firstname);
	 
			// lastname elements
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode("mook kim"));
			staff.appendChild(lastname);
	 
			// nickname elements
			Element nickname = doc.createElement("nickname");
			nickname.appendChild(doc.createTextNode("mkyong"));
			staff.appendChild(nickname);
	 
			// salary elements
			Element salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode("100000"));
			staff.appendChild(salary);
	 */
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File root = Environment.getExternalStorageDirectory();
			StreamResult result = new StreamResult(new File(root, "save.xml"));
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
	 
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		}
	}


