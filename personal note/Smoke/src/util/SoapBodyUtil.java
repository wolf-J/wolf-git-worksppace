package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.reporters.Files;


public class SoapBodyUtil {
	
	private static Header respHeader=null;
	
	public static String getCookieOfUAT()
	{
		System.out.println("Start to get UAT, cookie.");
		String logInUrl="https://thcoast-uat.aia.biz/COAST_FE/security/login";
		Map<String,String> params=new HashMap<>();
		//params.put("logonId", "BGRR361");
		params.put("logonId", "ASNPG67");
		
		params.put("password", "");
		
		
		Map<String,String> header=new HashMap<>();
		
		while(respHeader==null)
		{
			System.out.println("Try to get UAT authorization.");
			respHeader=HttpsUtil.getAuthorization(logInUrl, header, params, "UTF-8");
		}
		
		System.out.println("Finish get the UAT cookie: "+respHeader.getValue());
		return respHeader.getValue();
	}
	
	
	public static String getCookieOfAuthorization(String url)
	{
		
		System.out.println("Start to get Coast cookie.");
		String loginSuffix="security/login";
		
		String logInUrl=url.substring(0, url.indexOf("task"))+loginSuffix;
		Map<String,String> params=new HashMap<>();
		if(url.contains("uat"))
			params.put("logonId", "BGRR361");
		else
			params.put("logonId", "ASNPG67");
		params.put("password", "");
		
		
		Map<String,String> header=new HashMap<>();
		
		System.out.println("login Url: "+logInUrl);
		
	/*	while(respHeader==null)
		{
			System.out.println("Try to get the authorization.");
			if(logInUrl.contains("https"))
				respHeader=HttpsUtil.getAuthorization(logInUrl, header, params, "UTF-8");
			else
				respHeader=HttpUtil.getAuthorization(logInUrl, header, params, "UTF-8");
		}*/
		
		if(respHeader==null)
		{
			System.out.println("Try to get the authorization.");
			if(logInUrl.contains("https"))
				respHeader=HttpsUtil.getAuthorization(logInUrl, header, params, "UTF-8");
			else
				respHeader=HttpUtil.getAuthorization(logInUrl, header, params, "UTF-8");
		}
		
		String JESSESIONID=respHeader.getValue().substring(0, respHeader.getValue().indexOf(";"));
		System.out.println("Finish get the cookie: "+JESSESIONID);
		return JESSESIONID;
	}
	
	
	
	
	
	public static String getCookieOfSIT()
	{
		System.out.println("Start to get SIT, cookie.");
		String logInUrl="http://10.65.5.44:9085/COAST_FE_SIT/security/login";
		Map<String,String> params=new HashMap<>();
		params.put("logonId", "ASNPH9K");
		params.put("password", "");
		
		Map<String,String> header=new HashMap<>();
		while(respHeader==null)
		{
			System.out.println("Try to get S authorization.");
			respHeader=HttpsUtil.getAuthorization(logInUrl, header, params, "UTF-8");
		}
		//CloseableHttpResponse res=HttpUtil.doPostFormRequest(logInUrl, params, "UTF-8");
		System.out.println("Finish get the SIT cookie."+ " cookie : "+respHeader.getValue());
		return respHeader.getValue();
	}
	
	
	
	public static String getRequestXml(String action,String agent,String parameterJson,String dataFile)
	{
		StringBuilder sb=new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cs.ipos.aia.com/\"><soapenv:Header/><soapenv:Body><ws:callAction>");
		sb.append("<actionType>");
		sb.append(action).append("</actionType>");
		sb.append("<agentCode>");
		sb.append(agent);
		sb.append("</agentCode>");
		sb.append("<parametersJson>");
		sb.append(parameterJson);
		sb.append("</parametersJson>");
		sb.append("<dataFile>");
		sb.append(dataFile);
		sb.append("</dataFile>");
		
		sb.append("</ws:callAction></soapenv:Body></soapenv:Envelope>");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println(getCookieOfUAT());
		//String result=HttpsUtil.doPostSSL("https://iaia-uat.aia.co.th/iPoS_CS_Thai_Staging/ServerWSService", getRequestXml("Jason","123564","jasoon",""));
		//String result=HttpUtil.doPostRequest("https://iaia-uat.aia.co.th/iPoS_CS_Thai_Staging/ServerWSService", new HashMap<String,String>(), getRequestXml("Jason","123564","jasoon",""), "UTF-8");
		
		//System.out.println(getRequestXml("ACT_CHECK_VERSION","152495","AwEsC+7rhQ2q/h7KHSyxVEUzxaoZRxI8yuUREgh1PJESDj56nE0wEkKD5b2mnjOUo+o0jurT2AjlDPNG29WRLwZWny2GhijChlFs6sC/h9os1GSkFJNoWQnHp0e3WjH8uh3Jg5NmnYUfbLAE9ukp2hkkv47SzpKa0SehEjH9qpGL8GiUUWy7TpxlnX/JwzPnG3jkm9OhPjbGk4KnP+52T4KQuGJDomt+LQwVSCL6svUS3HpEn4QCgNbqDfP8FnBZXRybCe4YYv7gfJmjd6pGAh3+pxnh6mZG+7vOdxsduAosSQ==",""));
		
		//Map<String,String> header=new HashMap<>();
		
		//String encryptDataFile=Files.readFile(new File("D:/decryptDataFile.txt"));
		
		// dataFile=JncryptorTool.encrypt(encryptDataFile, "29840ad5315c40baab688f1a59fe4fa1");
		
		//header.put("Cookie", "dtCookie=81B75BE571665F2F392B33F7E310B1DB|X2RlZmF1bHR8MQ");
		//System.out.println(HttpsUtil.doPostRequest("https://iaia-uat.aia.co.th/iPoS_CS_Thai_Staging/ServerWSService", header, getRequestXml("ACT_SUBMITION","152495","AwEwHgizsRwsMDoVnUD1rD/pdwxSTm2RcZOxOicEAkHbK93X7RRBMzQsz8wXTwN8ThN+AuFFGtCBjGA58wiJ5/Agm736JpSTJyFCvAysWIy9tKt/Pw4KrkolMO8vvJcVsk1AjBhsvOPoHjgPIgzbXqobsi73WuOCr7tCAohSYBAnSnEHFn3SvGJGKMToFvfl/mc=",""), "UTF-8"));
		
		//UAT Env
		//String result=HttpsUtil.doPostRequest("https://iaia-uat.aia.co.th/iPoS_CS_Thai_Staging/ServerWSService", header, getRequestXml("ACT_SUBMITION","152495","AwEwHgizsRwsMDoVnUD1rD/pdwxSTm2RcZOxOicEAkHbK93X7RRBMzQsz8wXTwN8ThN+AuFFGtCBjGA58wiJ5/Agm736JpSTJyFCvAysWIy9tKt/Pw4KrkolMO8vvJcVsk1AjBhsvOPoHjgPIgzbXqobsi73WuOCr7tCAohSYBAnSnEHFn3SvGJGKMToFvfl/mc=",dataFile), "UTF-8");
		
		//Sit env
		//String result=HttpUtil.doPostRequest("http://10.72.5.218/iPoS_CS_Thai_Staging/ServerWSService", header, getRequestXml("ACT_SUBMITION","152495","AwEwHgizsRwsMDoVnUD1rD/pdwxSTm2RcZOxOicEAkHbK93X7RRBMzQsz8wXTwN8ThN+AuFFGtCBjGA58wiJ5/Agm736JpSTJyFCvAysWIy9tKt/Pw4KrkolMO8vvJcVsk1AjBhsvOPoHjgPIgzbXqobsi73WuOCr7tCAohSYBAnSnEHFn3SvGJGKMToFvfl/mc=",dataFile), "UTF-8");

		//Files.writeFile(result, new File("D:/result.txt"));
		//System.out.println("done");
		
		//System.out.println(HttpsUtil.doGetStrReq("https://iaia-uat.aia.co.th/iPoS_CS_Thai_Staging/ServerWSService/ServerWSServiceService.wsdl", "UTF-8"));
		
		//System.out.println(getRequestXml("Jason","123564","jasoon",""));
	}

}
