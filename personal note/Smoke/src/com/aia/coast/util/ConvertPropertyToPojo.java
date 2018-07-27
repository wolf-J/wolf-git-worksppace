package com.aia.coast.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.aia.coast.entity.Client;

public class ConvertPropertyToPojo {

	public static <T> T convertToPojo(Class<T> cls) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException
	{
		Properties property=ConfigUtil.getProperties(cls.getSimpleName()+".properties");
		Object tmp=cls.newInstance();
		Set<Entry<Object, Object>> entries=property.entrySet();
		
		String methodName=null;
		for(Entry<Object,Object> entry : entries)
		{
			String key=entry.getKey().toString();
			String value=entry.getValue().toString();
			
			//System.out.println("Key: "+key+", value: "+value);
			
			methodName="set"+key.substring(0, 1).toUpperCase()+key.substring(1);
			Method method=cls.getMethod(methodName, String.class);
			method.invoke(tmp, value);
		}
		
		
		return (T)tmp;
	}
	
	
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Properties property=ConfigUtil.getProperties("Client.properties");
		Client c=convertToPojo(Client.class);
		System.out.println(c.toString());
	}

}
