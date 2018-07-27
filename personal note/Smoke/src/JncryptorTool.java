


import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.JNCryptor;
import org.testng.reporters.Files;

import java.io.File;
import java.io.FileWriter;

import org.apache.xml.security.utils.Base64;
//import com.aia.ipos.cs.bean.CRUDRunGeneral;
//import com.aia.ipos.cs.util.SettingProperties;
//import com.aia.ipos.cs.util.StringUtil;

public class JncryptorTool {

	public static String decrypt(String dataEncodedBase64, String encyptKey) throws Exception{
		JNCryptor cryptor = new AES256JNCryptor();
		byte[] byte1 = null;
		
		byte1 = cryptor.decryptData(Base64.decode(dataEncodedBase64), encyptKey.toCharArray());
	
		return new String(byte1);
	}
	
	
	public static void main(String ...args) throws Exception
	{
		String dataFile=Files.readFile(new File("D:/temp.txt"));
		//System.out.println(decrypt("AwE5ZbUzJJjnlSSBVpQORXMrFw6bRCgOCgpYZ23UJ4k9+MrRD175VenSy6LiISUPW9hwO4NaniURa0RtVk7G9/y0IeuYvs4FQOqeOmgEM1MUZEcfXHfYZRvjovwkzKaUOjCkidbml19t113trxPnJRPoJokDT7BHwhxnD5ci4REjyDC95MQLmphfAQRp9gBXOvCFyMOYwytrklYZkcxzHgQmlK0/HiYVNYTp6n+OhVdDYejTYdv7cmJnJnXEr0vpqAUxQ589EqCLTjLpYv/jONq6fiBqh5XfJYceHctKtmgusg==","29840ad5315c40baab688f1a59fe4fa1"));
		
		Files.writeFile(decrypt(dataFile,"29840ad5315c40baab688f1a59fe4fa1"),new File("D:/temp_d.txt"));
		System.out.println("done");
		//writer.close();
	}
	/*public static byte[] decryptInByteOutByte(byte[] data, String encyptKey) throws Exception{
		JNCryptor cryptor = new AES256JNCryptor();
		byte[] byte1 = null;

		byte1 = cryptor.decryptData(data, encyptKey.toCharArray());
		
		return byte1;
	}
	*/
	
	public static String encrypt(String data, String encyptKey) throws Exception{
		JNCryptor cryptor = new AES256JNCryptor();
		byte[] byte1 = null;
		
		byte1 = cryptor.encryptData(data.getBytes(), encyptKey.toCharArray());

		return Base64.encode(byte1);
	}
	/*
	public static byte[] encryptOutByte(String data, String encyptKey) throws Exception{
		JNCryptor cryptor = new AES256JNCryptor();

		return cryptor.encryptData(data.getBytes(), encyptKey.toCharArray());
	}
	
	public static byte[] encryptInByteOutByte(byte[] data, String encyptKey) throws Exception{
		JNCryptor cryptor = new AES256JNCryptor();
		return cryptor.encryptData(data, encyptKey.toCharArray());
	}
	
	public static String getCryptKey(String processType, String agentCodeParam) {
			
		String cryptKey = "";
		if (processType.equals("ACTIVATION")) {
			for (int i = 0; i <10; i++) {
				cryptKey+=agentCodeParam;
			}
			
			if(cryptKey.length()<32){
				for (int i = cryptKey.length(); i < 32; i++) {
					cryptKey+="0";
				}
			}else{
				cryptKey=cryptKey.substring(0, 32);
			}
		}
		else if (processType.equals("TimeStamp")) {
			cryptKey = SettingProperties.getCipher();
		}
		else {
			if (StringUtil.isBlank(agentCodeParam)) {
				cryptKey = SettingProperties.getCipher();
			}
			else {
				cryptKey = new CRUDRunGeneral().fetcheRefMasterKey(agentCodeParam, processType);	
			}
		}
		
		return cryptKey;
	}
	*/
}