import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  
public class anchorTag{  
    public static void main( String[] args ) throws IOException{  
    	Document doc = Jsoup.connect("http://pec.ac.in").get();
    	Writer writer = null;
    	try {
      	String filePath = "anchorTags.csv";
      	File file = new File(filePath);
      	 
        writer = new BufferedWriter(new FileWriter(file));
        String text="Tag,URL\n";
        writer.write(text);
        Elements links = doc.getElementsByTag("a");
    	for (Element link : links) {
    		
    		String linkText = link.text();
    		String linkHref = link.attr("href");
    		
    		if(linkHref.length() < 2)
    			continue;
//    		System.out.println(linkText);
//    		System.out.println(linkHref);
    		text = linkText+","+linkHref+"\n";
            writer.write(text);
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