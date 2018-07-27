package util;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;


public class PDFUtil {
	
	public static String getPdfText(String filename)
	{
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PDDocument document = null;
		try
		{
		/*	PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream("C:/test.pdf"));
			
			parser.parse();
			
			document = parser.getPDDocument();
			*/
			
			
			/*
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(14);
			stripper.setEndPage(14);
			String content = stripper.getText(document);
			System.out.println("document content : "+content);
			*/
			
			document = PDDocument.load(new File("C:/test.pdf"));
			PDDocumentCatalog catalog = document.getDocumentCatalog();
			
			
			PDPageTree pages=catalog.getPages();
			
			System.out.println("Page: "+pages.getCount());
			
			for(int i=0;i<pages.getCount();i++)
			{
				PDPage page= pages.get(i);
				if(page != null)
				{
					PDResources resource = page.getResources();
					
				}
			}
		
		}finally
		{
			if(document!=null)
				document.close();
		}
	}

}
