package com.aia.ui.utils;

import java.io.IOException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.ssl.HostnameVerifier;
import com.sun.net.ssl.HttpsURLConnection;

public class DisableSSLCertificationCheckUtil {

	private static final Logger logger=LoggerFactory.getLogger(DisableSSLCertificationCheckUtil.class);
	
	private DisableSSLCertificationCheckUtil()
	{}
	
	public static void disableChecks()
	{
		try
		{
			new URL("https://0.0.0.0/").getContent();
		}catch(IOException e)
		{}
		
		
		try
		{
			SSLContext sslc;
			sslc=SSLContext.getInstance("TLS");
			TrustManager[] trustMangerArray={new X509TrustManager(){

				@Override
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return new X509Certificate[0];
				}
				
			}};
			sslc.init(null, trustMangerArray, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){

				@Override
				public boolean verify(String arg0, String arg1) {
					// TODO Auto-generated method stub
					return true;
				}
				
			});
			
		}catch(Exception e )
		{
			logger.error("error msg: "+e.getMessage());
			throw new IllegalArgumentException("Certification verification error.");
		}
	}
	
	public static void main(String ...args)
	{
		disableChecks();
	}
}
