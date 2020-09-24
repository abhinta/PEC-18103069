import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import JsoupCrawl.QueueNode;

import java.io.File;
import java.io.FileOutputStream;


import java.util.*;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class faculty {
	
	public class QueueNode{
		String a;
		int depth;
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		 String u="https://www.pec.ac.in/";
		 Queue<QueueNode> q=new LinkedList<>(); 
		 faculty outer = new faculty();
		 faculty.QueueNode inner = outer.new QueueNode();
		 inner.a=u;
		 inner.depth=1;
		 q.add(inner);
	
			
		 XSSFWorkbook workbook = new XSSFWorkbook();
		 XSSFSheet spreadsheet = workbook.createSheet( " Faculty data ");

		 XSSFRow row;
		 String[] keywords= {"/departments","/department","/centres","centre","/faculty","/aero","/cse","/civil","/ee","/ece","/me","/metta","/pie","/applied-sciences"};
		int count=0;
		HashSet<String> hs = new HashSet<String>();
		hs.add(u);
		while(!q.isEmpty())
		{	
			
			 QueueNode popped=q.poll();
			String url=popped.a;
			Document document = Jsoup.connect(url).get();
				

		
	
		
		 if(url.contains("faculty"))
		 {
			 
		 
		 Elements text=document.select("div:has(>strong)");
		 for(Element t:text)
		 {
			 if(t.select("strong").size()>2)
		 {
			 count=count+1;

			 row=spreadsheet.createRow(count);
   		  		int cellid = 0;
	            Cell cell_1 = row.createCell(cellid++);
	            cell_1.setCellValue(t.select("strong").get(0).text());
	            Cell cell_2 = row.createCell(cellid++);
	            cell_2.setCellValue(t.select("strong").get(1).text());
	            Cell cell_3 = row.createCell(cellid++);
	            cell_3.setCellValue(t.select("strong").get(2).text());
	            
		 }
			 
		 }
		 }
		 else
		 {
			 Elements links=document.select("a[href]");
			 for(Element link:links)
			 {
				 String relHref = link.attr("href"); 
		         String absHref = link.attr("abs:href");
		         int len=absHref.length();
		         if(popped.depth<10)
		         {
		        	 if(relHref.length()>1)
		        		 if((!hs.contains(absHref))&&(relHref.charAt(0)!='#')&&(absHref.startsWith(u))&&(absHref.charAt(len-4)!='.')&&(!absHref.endsWith(".docx"))&&(!absHref.endsWith(".jpeg")))
		        	{
		        	 for(int i=0;i<14;i++)
		        	 {
		        		if(absHref.endsWith(keywords[i])||absHref.contains("/faculty/"))
		        		{
				       		 faculty out_temp = new faculty();
				    		 faculty.QueueNode inner_temp = out_temp.new QueueNode();
				    		 inner_temp.a=absHref;
				    		 inner_temp.depth=popped.depth+1;
//				    		 System.out.println(absHref);
				    		 q.add(inner_temp);
				    		 break;
		        		}
		        	 }
		    		 hs.add(absHref);
		        	}
		         }
		         
		         
		         
		         
			 }
			 
		 }
		}
		
		

	      String home = System.getProperty("user.home");
	
	      FileOutputStream out = new FileOutputStream(
	         new File(home+"\\Desktop\\PecFaculty.xlsx"));
	      
	      workbook.write(out);
	      out.close();
	      System.out.println("PecFaculty.xlsx written successfully");
	}

}
