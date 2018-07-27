package com.aia.coast.entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.aia.coast.util.ConvertPropertyToPojo;

public class uatIndividualPolicy extends Policy{
	
	public uatIndividualPolicy()
	{
		super();
	}
	
	
	//test
	public static void main(String ...args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException
	{
		Policy policy=ConvertPropertyToPojo.convertToPojo(uatIndividualPolicy.class);
		Method[] methods=policy.getClass().getMethods();
		
		for(Method m: methods)
		{
			if(m.getName().contains("get"))
			{
				System.out.println(m.invoke(policy, null));
			}
		}
	}
	
}
