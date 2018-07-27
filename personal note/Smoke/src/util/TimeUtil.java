package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;



public final class TimeUtil {
	
	private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;
	
	private TimeUtil(){}
	
	public static LocalDateTime parseTime(String timeStr)
	{
		return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
	}
	
	public static LocalDateTime parseTime(String timeStr, TimeFormat format)
	{
		return LocalDateTime.parse(timeStr,format.formatter);
	}
	
	public static String parseTime(LocalDateTime time)
	{
		return DEFAULT_DATETIME_FORMATTER.format(time);
	}
	
	public static String parseTime(LocalDateTime time, TimeFormat format)
	{
		return format.formatter.format(time);
	}
	
	public static String getCurrentTime()
	{
		return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
	}
	
	public static String getCurrentTime(TimeFormat format)
	{
		return format.formatter.format(LocalDateTime.now());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(getCurrentTime(TimeFormat.SHORT_DATE_PATTERN_LINE));
		/*Assert.assertEquals(ldt, pldt);
		String now = TimeUtil.getCurrentTime(TimeUtil.TimeFormat.LONG_DATE_PATTERN_SLASH);
		System.out.println(now);*/
	}
	
	public enum TimeFormat
	{
		SHORT_DATE_PATTERN_LINE("MM-dd-yyyy"),
		SHORT_MONTH_DATE_YEAR_PATTERN_LINE("yyyy-MM-dd"),
		SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),
		SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
		SHORT_DATE_PATTERN_NONE("yyyyMMdd"),
		
		LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
		LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
		LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),
        
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");
		
		private transient DateTimeFormatter formatter;
		
		TimeFormat(String pattern)
		{
			formatter= DateTimeFormatter.ofPattern(pattern);
		}
	}
	
	
	

}
