package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import sun.reflect.generics.scope.MethodScope;

import com.aia.coast.entity.Member;

public class ReadExcelUtil<T> {
	private InputStream inputStream=null;
	private Sheet sheet=null;
	
	private int headerRowNum=0;
	private int startRow=0;
	private int lastRowNumber=0;
	List<T> dataSet;
	T data=null;
	
	public void enablePageRead(File file, int sheetIndex, int startRow, int headerRowNum)
	{
		this.startRow=startRow;
		this.headerRowNum=headerRowNum;
		Workbook wb;
		try
		{
			inputStream=new FileInputStream(file);
			wb=WorkbookFactory.create(inputStream);
			sheet=wb.getSheetAt(sheetIndex);
			
		}catch(InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lastRowNumber=sheet.getLastRowNum()+1;
		dataSet=new ArrayList(lastRowNumber-startRow);
	}
	
	public List<T> getDataSet() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		Class<T> clz=null;
		List<String> setterList=new ArrayList();
		Type type=this.getClass().getGenericSuperclass();
		ParameterizedType parametrizedType=ParameterizedType.class.cast(type);
		for(Type t : parametrizedType.getActualTypeArguments())
		{
			System.out.println(t.getTypeName());
			
			clz=(Class<T>) t;
		}
		Method[] methods=clz.getDeclaredMethods();
		String methodName=null;
		for(int i=0, len=methods.length;i<len;i++)
		{
			methodName=methods[i].getName();
			if(methodName.contains("set"))
			{
				setterList.add(methodName.substring(3));
			}
		}
		
		Row headerRow=sheet.getRow(headerRowNum);
		int columnLen=headerRow.getPhysicalNumberOfCells()-1;
		String fieldName=null;
		for(int j=startRow;j<lastRowNumber;j++)
		{
			data=clz.newInstance();
			Row dataRow=sheet.getRow(j);
			for(int i=0; i<columnLen;i++)
			{
				fieldName=getCellValue(headerRow.getCell(i));
				if(setterList.contains(fieldName))
				{
					Method m = clz.getMethod("set"+fieldName, String.class);
					m.invoke(data, getCellValue(dataRow.getCell(i)));
				}
			}
			dataSet.add(data);
		}
		
		return dataSet;
	}

	
	public static void main(String ...args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		ReadExcelUtil rcu=new ReadExcelUtil<Member>(){};
		rcu.enablePageRead(new File("D:\\Project\\THUATRegressionCoast\\Single630.xlsx"),0,2,1);
		List<Member> l=rcu.getDataSet();
		//System.out.println(JsonUtil.formatObjectToJson(l));
	}
	
	private static String getCellValue(Cell cell){  
        String value = "";  
        if(cell!=null){  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:  
                if(HSSFDateUtil.isCellDateFormatted(cell)){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());  
                    value = sdf.format(date);  
                }else{  
                    Double data = cell.getNumericCellValue();  
                    value = data.toString();  
                }  
                break;  
            case Cell.CELL_TYPE_STRING:  
                value = cell.getStringCellValue();  
                break;  
            case Cell.CELL_TYPE_BOOLEAN:  
                Boolean data = cell.getBooleanCellValue();  
                value = data.toString();   
                break;  
            case Cell.CELL_TYPE_ERROR:  
                System.out.println("Cell Type Error");  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                value = String.valueOf(cell.getNumericCellValue());    
                if (value.equals("NaN")) {
                	
                    value = cell.getStringCellValue().toString();    
                }             
                break;            
            case Cell.CELL_TYPE_BLANK:  
                //System.out.println("锟斤拷元锟斤拷锟斤拷锟斤拷 为锟斤拷值 ");  
                break;            
            default :  
                value = cell.getStringCellValue().toString();  
                break;  
            }  
        }  
        return value;  
    }  
}
