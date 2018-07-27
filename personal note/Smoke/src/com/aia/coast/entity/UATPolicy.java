package com.aia.coast.entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.Locator;

public class UATPolicy extends Policy{
	

	//test
	public static void main(String ...args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException
	{
		UATPolicy policy=ConvertPropertyToPojo.convertToPojo(UATPolicy.class);
		System.out.println(policy.getPolicyCategory());
	}
	
}
