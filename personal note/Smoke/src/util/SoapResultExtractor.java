package util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.reporters.Files;

public class SoapResultExtractor {
	
	public static String extractReturnMessage(String result)
	{
		Pattern p=Pattern.compile(".*<return>(.*)</return>.*");
		Matcher match=p.matcher(result);
		if(match.find())
		{
			System.out.println("yes");
			return match.group(1);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String result=Files.readFile(new File("D:/result.txt"));
		System.out.println(result);
		System.out.println(extractReturnMessage(result));
	}

}
