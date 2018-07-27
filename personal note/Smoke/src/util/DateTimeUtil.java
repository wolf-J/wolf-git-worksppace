package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateTimeUtil {

	public static final String TIMEFORMAT="MM\\/dd\\/YYYY HH:mm:ss";
	
	public static String timestamp()
	{
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmSSss");
		Date now = new Date();
		try
		{
			Thread.sleep(13l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdf.format(now);
	}
	
	public static String getCreateTime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat(TIMEFORMAT);
		Date now=new Date();
		return sdf.format(now);
	}
	
	
	
	public static String getUpdateTime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat(TIMEFORMAT);
		Date now=new Date();
		return sdf.format(now);
	}
	
	public static String refNoTime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMYYYY");
		Date now=new Date();
		return sdf.format(now)+"-"+randomNum(99);
	}
	
	public static String eappformid()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMYYYY");
		Date now=new Date();
		return sdf.format(now)+"-"+randomNum(99);
	}
	
	public static String getEffectiveDate()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("MM\\/dd\\/YYYY 00:00:00");
		Date now=new Date();
		
		now.setDate(-1);;
		return sdf.format(now);
	}
	
	
	public static int randomNum(int max)
	{
		Random rand=new Random();
		return rand.nextInt(99);
	}
	
	public static String longToDate(long time)
	{
		Date date=new Date();
		date.setTime(time);
		System.out.println(date);
		return date.toString();
	}
	
	public static String dateIdentity()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("MMddHHmm");
		Date now = new Date();
		return sdf.format(now);
	}
	
	public static String increaseOneYear(String date)
	{
		Calendar calendar=Calendar.getInstance();
		String[] yearMonDay=date.split("-");
		calendar.set(Integer.parseInt(yearMonDay[2]), Integer.parseInt(yearMonDay[0])-1, Integer.parseInt(yearMonDay[1]));
		calendar.add(Calendar.YEAR, 1);
		DateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
		return format1.format(calendar.getTime());
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
		increaseOneYear("01-01-2017");
		
		//System.out.println(dateIdentity());
		//System.out.println(refNoTime());
	}

}
