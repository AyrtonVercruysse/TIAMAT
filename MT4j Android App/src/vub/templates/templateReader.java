package vub.templates;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;

import vub.ast.Function;
import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;

/*
 * StartTiamat.functions.add(IfThenElse());
public Templates IfThenElse() {
	String names[] = { "if:", "then:", "else:" };
	String contents[] = { "predicate", "consequent", "alternative" };
	vub.ast.Node node = new vub.ast.Function(null, names, contents);
	return new Templates("If:Then:Else", node);
}
*/

public class templateReader{

	public static void templateReader() {
	}
	
		
	public static void read(AssetManager assetManager){
		try {
			System.out.println("Tesss");
			InputStream is = assetManager.open("templates.xml");
			System.out.println("Tesss");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			dbFactory.setValidating(true);
			dbFactory.setIgnoringElementContentWhitespace(true);
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			Node file = doc.getFirstChild(); //Templates
			removeWhitespaceNodes((Element) file);
			NodeList templates = file.getChildNodes(); //Template
			for (int i = 0; i < templates.getLength(); i++){
				Element template = (Element) templates.item(i); //Template
				String name =  template.getAttribute("name");
				template = (Element) template.getFirstChild(); //Type
				String type = template.getNodeName();
				Class argumentsTypes = Class.forName(type); // e.g. Function
				
				Constructor argumentConstructor = argumentsTypes.getConstructor(Element.class);
				vub.ast.Node func = (vub.ast.Node)argumentConstructor.newInstance(template); // Node
				System.out.println("Temp" + type);
				if(type.equals("vub.ast.Function")){
					StartTiamat.functions.add(new Templates(name, func));
				}else if(type.equals("vub.ast.Operation")){
					StartTiamat.operations.add(new Templates(name, func));
				} else{
					StartTiamat.definitions.add(new Templates(name, func));
				}
				
			}
		} catch (Exception ex) {
			System.out.println("TemplatesError");
			ex.printStackTrace();
		}
	}
	
	
	public static void readSave(AssetManager assetManager){
	try {
		System.out.println("Loading231");
		//InputStream is = assetManager.open("save.xml");
		
		File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "save.xml");
		System.out.println("Loading231");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		System.out.println("Loading231");
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		dbFactory.setValidating(true);
		System.out.println("Loading231");
		dbFactory.setIgnoringElementContentWhitespace(true);
		Document doc = dBuilder.parse(file2);
		doc.getDocumentElement().normalize();
		Node file = doc.getFirstChild(); //Templates
		removeWhitespaceNodes((Element) file);
		System.out.println("Loading231");
		NodeList templates = file.getChildNodes(); //Template
		Element template = (Element) templates.item(0); //Template
		//String name =  template.getAttribute("name");
		template = (Element) template.getFirstChild(); //Type
		String type = template.getNodeName();
		Class argumentsTypes = Class.forName(type); // e.g. Function
		Constructor argumentConstructor = argumentsTypes.getConstructor(Element.class);
		vub.ast.Node func = (vub.ast.Node)argumentConstructor.newInstance(template); // Node
		System.out.println("Loading231");
		Tiamat.main = func;
		Tiamat.redraw();
	} catch (Exception ex) {
		System.out.println("TemplatesError");
		ex.printStackTrace();
	}
	//Editable value = input.getText();
	//ActionReply = value.toString();
	
	
	// Do something with value!
}

	public static void removeWhitespaceNodes(Element e) {
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
		Node child = children.item(i);
		if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
		e.removeChild(child);
		}
		else if (child instanceof Element) {
		removeWhitespaceNodes((Element) child);
		}
		}
		}
}
