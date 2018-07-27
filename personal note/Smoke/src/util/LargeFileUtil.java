package util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeFileUtil {

	public static void method4(String fileName)
	{
		RandomAccessFile raf=null;
		FileChannel channel=null;
		
		try
		{
			raf=new RandomAccessFile(fileName,"rw");
			channel=raf.getChannel();
			
			long startTime=System.currentTimeMillis();
			ByteBuffer buf=ByteBuffer.allocate((int)raf.length());
			buf.clear();
			channel.read(buf);
			long endTime=System.currentTimeMillis();
			System.out.println("Read Time: "+(endTime-startTime)+"ms");
		}catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				if(raf!=null)
				{
					raf.close();
				}
				if(channel!=null)
				{
					channel.close();
				}
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public static void method3(String fileName)
	{
		RandomAccessFile raf=null;
		FileChannel channel=null;
		
		try
		{
			raf=new RandomAccessFile(fileName,"rw");
			channel=raf.getChannel();
			
			long startTime=System.currentTimeMillis();
			
			MappedByteBuffer buf=channel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
			
			long endTime=System.currentTimeMillis();
			System.out.println("Read Time: "+(endTime-startTime)+"ms");
		}catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				if(raf!=null)
				{
					raf.close();
				}
				if(channel!=null)
				{
					channel.close();
				}
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		method3("D:/CS ipos Thailand UAT Distribution.ipa");
		System.out.println("=========================================================");
		method4("D:/CS ipos Thailand UAT Distribution.ipa");
	}

}
