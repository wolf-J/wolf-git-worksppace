package util;

public class Checker {
	public static boolean isNull(Object object)
	{
		if(object==null || object.toString().isEmpty())
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public static boolean isNotNull(Object object)
	{
		return !isNull(object);
	}
}
