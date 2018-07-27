package util;

import java.io.PrintStream;

import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.helpers.DefaultHandler;

public class XLSXConvertCSVReader {
	enum xssfDataType{
		BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER
	}
	
	class AIAXSSFSheetHandler extends DefaultHandler{
		private StylesTable stylesTable;
		
		private ReadOnlySharedStringsTable sharedStringsTable;
		
	//	private final PrintStream output;
		
		//private final int minColumnCount;
		
		private boolean vIsOpen;
		
		
	}
}
