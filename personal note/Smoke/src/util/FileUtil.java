package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class FileUtil {
	public static String readFileAndConvertToBase64(String filename) throws IOException
	{
		byte[] bytes=Files.readAllBytes(Paths.get(filename));
		return Base64.encode(bytes);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
