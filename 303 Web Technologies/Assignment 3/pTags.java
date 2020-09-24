import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.stage.FileChooser;  
public class pTags{  
    public static void main( String[] args ) throws IOException{  
    	Document doc = Jsoup.connect("http://pec.ac.in").get();
    	Writer writer = null;
    	try {
      	String filePath = "pTags.csv";
      	File file = new File(filePath);
      	 
        writer = new BufferedWriter(new FileWriter(file));
        String text="Tag,Text\n";
        writer.write(text);
        Elements rows = doc.getElementsByTag("p");
    	for (Element row : rows) {
    		//System.out.println(row);
    		if(row.text().length() > 1)
    		{
//    			System.out.println(row.text());
//    			System.out.println();
    			text = "<p>,"+row.text()+"\n";
                writer.write(text);
    		}
    	}
      } catch (Exception ex) {
          ex.printStackTrace();
      }
      finally {
         
          writer.flush();
          writer.close();
      }
    }  
} 