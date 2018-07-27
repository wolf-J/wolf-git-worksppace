package com.aia.coast.testcase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestHttpClient {

	public static String doLogin(String url,String logonId,String password) throws Exception { 
		   
	        CloseableHttpClient httpClient = HttpClients.createDefault();  
	        String httpStr = null;  
	        HttpPost httpPost = new HttpPost(url);  
	       
	        CloseableHttpResponse response = null;  
	        String Cookie="";
	        int StatusCode=0;
	        try {  
	            //httpPost.setConfig(requestConfig);  
	            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();  
	            pairList.add(new BasicNameValuePair("logonId",logonId));
	            pairList.add(new BasicNameValuePair("password",password));
	            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));  
	            response = httpClient.execute(httpPost);  
	          
				StatusCode = response.getStatusLine().getStatusCode();
				
	            if (StatusCode > 300) {

	            	httpPost.abort();

	                throw new RuntimeException("Error status code :" + StatusCode);

	            }
				Header[] Cookies= null;
				try{
					if(response.getFirstHeader("Set-Cookie")!=null)  
		            {
					   Cookies = response.getHeaders("Set-Cookie");										 
		            }
				    StringBuffer StringCookie = new StringBuffer();
		            for (int j = 0; j < Cookies.length; j++) {  
		                	 Cookie=StringCookie.append(Cookies[j].getValue()).append(";").toString(); 
		                } 
				}catch(Exception e){
					
					throw new Exception("session value is null");
					
				}	
				int statusCode = response.getStatusLine().getStatusCode();
				
				
	            HttpEntity entity = response.getEntity();
	    	           
	            httpStr = EntityUtils.toString(entity, "UTF-8");  
	            //System.out.println("loginHttpStr"+httpStr);
	            
	            if(httpStr.contains("COAST Home Page")){
	        		System.out.println("Login Success");
	        	}
	        	else{	       
	        	throw new Exception("Login Failed !!!");
	        	}
	            
	        } catch (IOException e) {  
	        	 throw new RuntimeException("URL Connect failded !!!");
	        } finally {  
	            if (response != null) {  
	                try {  
	                    EntityUtils.consume(response.getEntity());  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return Cookie;  
	    }  
	  
	   public static String getJobList(String getJobListUrl,String session,String logonId,String status,String flg ) throws Exception { 
	        CloseableHttpClient httpClient = HttpClients.createDefault();  
	        String httpStr = null;  
	        HttpPost httpPost = new HttpPost(getJobListUrl);  
	       
	        CloseableHttpResponse response = null;  
	       
	        try {  
	            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();  
	            pairList.add(new BasicNameValuePair("userId",logonId));
	            pairList.add(new BasicNameValuePair("status",status));
	            pairList.add(new BasicNameValuePair("flg",flg));
	            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));  
	            
	            if(session!=null && session.length()>0){
	            	httpPost.addHeader("Cookie", session);
	            }else{
	            	throw new Exception("session value is null");
	            }
	            response = httpClient.execute(httpPost);  
	          
				response.getStatusLine().getStatusCode();
			
				System.out.println("StatusCode:"+response.getStatusLine().getStatusCode());
				
	            HttpEntity entity = response.getEntity();
	         
	           
	            httpStr = EntityUtils.toString(entity, "UTF-8");  
	            if(!httpStr.substring(httpStr.lastIndexOf("policyNo")+11, httpStr.lastIndexOf("policyName")+-3).isEmpty()){
	        		System.out.println("Get Job List Success!!!");
	        	}
	        	else{	       
	        		throw new Exception("Get Job List Failed !!!");
	        	}
	           // System.out.println("httpStraa:"+httpStr);
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (response != null) {  
	                try {  
	                    EntityUtils.consume(response.getEntity());  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return httpStr;  
	    }  

	   public static String createNewtask(String createNewtaskUrl,String session){

	    	String result = "";
	    	int statusCode = 0;
	    	CloseableHttpClient httpClient = HttpClients.createDefault();     	   	
	        try {

	            HttpGet httpGet = new HttpGet(createNewtaskUrl);
	           if(session!=null && session.length()>0){
	        	   httpGet.addHeader("Cookie", session);
	           }else{
	        	   throw new Exception("session value is null !!!");
	           }
	           
	            CloseableHttpResponse response = httpClient.execute(httpGet);
	            
	             statusCode = response.getStatusLine().getStatusCode();
	            	          
	            if (statusCode > 300) {
	                httpGet.abort();
	                throw new RuntimeException("HttpClient,error status code :" + statusCode);
	            }
	            
	            HttpEntity entity = response.getEntity();	           
	            result = EntityUtils.toString(entity, "UTF-8"); 
	            
	            if(!result.substring(result.lastIndexOf(":")+2, result.length()-2).isEmpty()){
	        		System.out.println("Create New Task Success!!!");
	        	}
	        	else{	       
	        	throw new Exception("Create New Task Failed !!!");
	        	}
	            	            
	            EntityUtils.consume(entity);

	            response.close();

	        } catch (Exception e) {

	        	throw new RuntimeException("URL Connect failded !!!");
	        }
	        return result;

	    }
	   public static byte[] downLoadTemplate(String url,int startRow,String excelField){
	    	String result = "";
	    	int statusCode= 0; 
	    	CloseableHttpClient httpClient = HttpClients.createDefault();  
	    	byte[] bytes=null;	    	
	        try {
	            HttpGet httpGet = new HttpGet(url);	            
	            CloseableHttpResponse response = httpClient.execute(httpGet);	            
	            statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode > 300) {
	                httpGet.abort();
	                throw new RuntimeException("HttpClient,error status code :" + statusCode);
	            }
	            String h1=response.getFirstHeader("Content-Disposition").getValue();
				String fileName=h1.substring(h1.indexOf("=")+2,h1.length()-1);
				System.out.println("fileName:"+fileName);				
	            HttpEntity entity = response.getEntity();            
	            bytes=EntityUtils.toByteArray(entity);            
	            System.out.println("Content Length: "+bytes.length);
	            EntityUtils.consume(entity);
	            response.close();
	          //save
	           FileOutputStream  out= new FileOutputStream(System.getProperty("user.dir")+File.separator+fileName);	            
	            out.write(bytes);	           	                    
	            out.flush();
	            out.close(); 
	            
	           XSSFWorkbook  workbook = new XSSFWorkbook ();
	           FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+File.separator+fileName);
	           try{     
	            //open
	    		workbook=new XSSFWorkbook(fin);	    		
	    		XSSFSheet sheet=workbook.getSheetAt(0);
	    		int lastRow=sheet.getLastRowNum();
	    		XSSFRow titleRow=sheet.getRow(startRow);	
       
	    		String sheetName = sheet.getSheetName();
	    		System.out.println("sheetName:"+sheetName+"   lastRow:"+lastRow);
	    		String cellValue = null;
    			StringBuilder sb = new StringBuilder();
    			int CellNum = titleRow.getLastCellNum();
    			System.out.println("total "+CellNum+" field");
	    		for(int cellnum = 0; cellnum<titleRow.getLastCellNum();cellnum++){
	    			cellValue = titleRow.getCell(cellnum).getStringCellValue();
	    			sb.append(cellValue);
	    			System.out.print(cellValue+"  ");	    			
	    		}	
	    		String[] strlen= excelField.split(",");	
	    		String sbStr =sb.toString();
	    		for(int j=0;j<strlen.length;j++){	  
	    			while(!sbStr.contains(strlen[j])){
	    				throw new Exception("The excel has error : lost "+strlen[j] +" field !!!");
	    			}
	    		}    		
	    		}  
	    		catch( IOException e){
	    			 throw new RuntimeException("Open Excel RunTimeException !!!");
	    		}finally{
	    			fin.close();
	    		}
	        } catch (Exception e) {
	        	throw new RuntimeException("URL Connect failded !!!");
	        }
	        return bytes;	   
	   } 
	  
	public static void main(String args[]) throws Exception{	
		//Login 
		String url="https://10.65.5.50:9444/COAST_FE_PRESIT0531/security/login";
		String logonId="ASNPG67";
		String password="";
		String session=doLogin(url,logonId,password);
		System.out.println("session:"+session);
		
		//get Job List
		//System.out.println("-----  get Job List  -------");
		String url2="https://10.65.5.50:9444/COAST_FE_PRESIT0531/task/taskOperate/getTask";
		String status="1";
		String flg="todo";
		getJobList(url2,session,logonId,status,flg );
		
		//create New Task
		System.out.println("-----  create New Task  -------");
		String url3="https://10.65.5.50:9444/COAST_FE_PRESIT0531/task/taskOperate/getNewTask?stepName=NB_001&_="+System.currentTimeMillis();
		createNewtask(url3,session);
		
		String Url1="https://10.65.5.50:9444/COAST_FE_PRESIT0531/form/memberFileUpload/download?downloadTemplate=Y&templateFileType=DownloadTemplate1";
		String Url2="";
		downLoadTemplate(Url1,1,"");
		
		
	}
		
	
}
