package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StrUtil {
	/*
	 * Hi, Jason
	 */
	public static String trimAllWhiteSpace(String str)
	{
		if(str!=null)
		{
			int len=str.length();
			if(len>0)
			{
				char[] dest=new char[len];
				int desPos=0;
				for(int i=0;i<len;i++)
				{
					char c=str.charAt(i);
					if(!Character.isWhitespace(c))
					{
						dest[desPos++]=c;
					}
				}
				return new String(dest,0,desPos);
			}
		}
		return str;
	}
		
	public static String ManipulateBenefitCode(String benefitCode)
	{
		String[] benefitCodeSplit=benefitCode.split("-");
		StringBuilder builder=new StringBuilder();
		for(int i=0;i<benefitCodeSplit.length;i++)
		{
			if(i==0 && !Character.isWhitespace(benefitCodeSplit[i].charAt(benefitCodeSplit[i].length()-1)))
				builder.append(benefitCodeSplit[i]+" ");
			else if(i==0)
			{
				builder.append(benefitCodeSplit[i]);
			}else if(i==1 && !Character.isWhitespace(benefitCodeSplit[i].charAt(0)))
			{
				builder.append("- "+benefitCodeSplit[i]);
			}else
			{
				builder.append("-"+benefitCodeSplit[i]);
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ManipulateBenefitCode("VOLML -VOL GL"));
	}
	
	
	
	//Another method
	public static String trimAllWhiteSpace1(String str)
	{
		if(str.length()<=0)
		{
			return str;
		}
		StringBuilder sb=new StringBuilder(str);
		int index=0;
		while(sb.length()>index)
		{
			if(Character.isWhitespace(sb.charAt(index)))
			{
				sb.deleteCharAt(index);
			}else
			{
				index++;
			}
		}
		return sb.toString();
	}

}
