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

import vub.ast.Function;
import vub.tiamat.StartTiamat;

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

	public static void templateReader(AssetManager assetManager) {
		try {
		
			InputStream is = assetManager.open("templates.xml");
					
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
				Element template = (Element) templates.item(i); 
				String name = template.getAttribute("name");
				String sort = template.getAttribute("type");
				template = (Element) template.getFirstChild();
				String type = template.getNodeName();
				Class function = Class.forName(type);
				Constructor constructor = function.getConstructors()[1];
				NodeList args = template.getElementsByTagName("args").item(0).getChildNodes();
				System.out.println("Templ: " + name);				
				int nrOfArgs = args.getLength();
				String names[] = new String[nrOfArgs];
				vub.ast.Node contents[] = new vub.ast.Node[nrOfArgs];
				for (int j = 0; j < nrOfArgs; j++){
					Element argument = (Element) args.item(j);
					
					String argumentName = argument.getNodeName();
					names[j] = argument.getAttribute("name");
									
					Class argumentsTypes = Class.forName(argumentName);
					Constructor argumentConstructor = argumentsTypes.getConstructors()[0];
					vub.ast.Node aerg = (vub.ast.Node)argumentConstructor.newInstance(null, argument.getAttribute("hint"));
					contents[j] = aerg;
				}
					
				vub.ast.Node tester = (vub.ast.Node)constructor.newInstance(null, names, contents);
				StartTiamat.functions.add(new Templates(name, tester));
			}
	
		} catch (Exception ex) {
			System.out.println("TemplatesError");
			ex.printStackTrace();
		}
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
