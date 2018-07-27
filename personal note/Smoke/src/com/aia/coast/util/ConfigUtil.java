package com.aia.coast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil{


	public static Properties getProperties(String config) throws IOException {
		Properties properties = new Properties();
		FileInputStream inStream = new FileInputStream(new File(config));
		
		
		properties.load(inStream);
		return properties;
	}
	
	public static Properties getProjectProperties(String config)
	{
		
		InputStream in=null;
		try {
			in = new FileInputStream(System.getProperty("user.dir")+File.separator+config);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Properties properties=new Properties();
		try
		{
			properties.load(in);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return properties;
		
		
	}
	
	public static void main(String ...args)
	{
		System.out.println(System.getProperty("user.dir")+File.separator+"Bill.properties");
	}
}
