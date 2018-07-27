package util;

import java.lang.reflect.Type;

import com.aia.cs.ipos.entity.Contact;
import com.aia.cs.ipos.entity.Pdf;
import com.aia.cs.ipos.entity.ProposalInfo;
import com.aia.cs.ipos.entity.SubProposalInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
	
	private static Gson gson;
	private static JsonParser parser;
	
	static 
	{
		//gson=new Gson();
		gson=new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		//gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		parser=new JsonParser();
	}
	
	public static String formatObjectToJson(Object obj)
	{
		//return gson.toJson(obj, Contact.class);
		return gson.toJson(obj);
		
	}
	
	public static String formatObjectToJson1(Object obj)
	{
		//return gson.toJson(obj, Contact.class);
		Gson gson1=new Gson();
		return gson1.toJson(obj);
	}
	
	public static<T> Object formatJsonToObject(String json,Class<T> clazz)
	{
		return gson.fromJson(json, clazz);
	}
	
	public static JsonArray formatStringToJsonArray(String json)
	{
		return parser.parse(json).getAsJsonArray();
	}
	
	
	public static JsonObject formatStringToJsonObject(String json)
	{
		return parser.parse(json).getAsJsonObject();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pdf[] pdf=new Pdf[3];
		for(int i=0;i<pdf.length;i++)
		{
			pdf[i]=new Pdf();
			pdf[i].setDoccontenten("EN");
			pdf[i].setDoccontentthai("Thai");
			pdf[i].setPdfid("id"+i);
			pdf[i].setCreatetime("");
			pdf[i].setUpdatetime("");
		}
		System.out.println(formatObjectToJson(pdf));
		
	}

}
