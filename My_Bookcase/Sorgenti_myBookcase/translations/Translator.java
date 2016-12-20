package translations;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Translator {
	private Hashtable<String, String> dictionary=new Hashtable<String, String>();
	
	public Translator(){
		String lang=System.getProperty("user.language")+".xml";
		System.out.println("My language file is :" + lang);
		
		if (this.getClass().getResource(lang)!=null){
			
			String filename=this.getClass().getResource(lang).getPath();
			SAXBuilder builder = new SAXBuilder();
			Document document;
			try {
				System.out.println(filename);
				document = builder.build(new File(filename));			
				Element root = document.getRootElement();
				Iterator<?> iterator = root.getChildren().iterator();
				
				while(iterator.hasNext()){
				    Element item = (Element)iterator.next();
				    String name=item.getName();
				    if (name.equals("file")){
				    	System.out.println("Reading translation file info: "+item.getText());
				    } else if (name.equals("dictionary")){
				    	Iterator<?> i2=item.getChildren().iterator();
				    	while (i2.hasNext()){
				    		Element row = (Element)i2.next();
				    		dictionary.put(row.getAttributeValue("key"), row.getText());
				    	}
				    }
				}
			} catch (JDOMException e) {
				e.printStackTrace();
				new Error(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				new Error(e.getMessage());
			}
		}
	}
	
	public String _(String key){
		String found=dictionary.get(key);
		if (found!=null){
			return found;
		}
		return key;
		
	}
	
	public String getKey(String text){
		Enumeration<String> keys = dictionary.keys();
		while (keys.hasMoreElements()){
			String key=keys.nextElement();
			if (dictionary.get(key).equals(text)){
				return key;
			}
		}
		return text;
	}
}
