package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
	public static List<String> diffSet(final List<String> listA, final List<String> listB)
	{
	
		List<String> list=new ArrayList<>();
		list.addAll(listA);
		
		list.removeAll(listB);
		
		return list;
	}
	
	public static List<String> diffList(final List<String> listA, final List<String> listB)
	{
		List<String> list=new ArrayList<>();
		if(listA.size()>listB.size())
		{
			list.addAll(listA);
			list.remove(listB);
		}else
		{
			list.addAll(listB);
			list.removeAll(listA);
		}

		return list;
	}
	
	public static void main(String ...args)
	{
		List<String> list1=Arrays.asList("M - Member");
		List<String> list2=Arrays.asList("M - Member","S - Spouse","C - Child");
		
		List<String> list=diffList(list1,list2);
		list.stream().forEach(item -> System.out.println(item));
	}

}
