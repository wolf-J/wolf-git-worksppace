package util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;




import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;
/**
 * 
 *	@author jason
 */
public class HttpsUtil {
    private static CloseableHttpClient httpClient = null;
    private static final String CHARSET = "UTF-8";
    private static final int timeout = 500000;
	
	public static CloseableHttpClient getIgnoreSslCertificateHttpClient() throws HttpException {
		System.setProperty ("jsse.enableSNIExtension", "false");
		SSLContext sslContext = null;
		try {
		  sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

		    @Override
		    public boolean isTrusted(final X509Certificate[] arg0, final String arg1)
		      throws CertificateException {

		      return true;
		    }
		  }).build();
		} catch (Exception e) {
		  throw new HttpException("can not create http client.", e);
		}
		//SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
		  //new NoopHostnameVerifier());
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"},null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
		  .<ConnectionSocketFactory> create()
		  .register("http", PlainConnectionSocketFactory.getSocketFactory())
		  .register("https", sslSocketFactory).build();
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connMgr.setMaxTotal(55550);
		connMgr.setDefaultMaxPerRoute(55550);
		RequestConfig requestConfig = RequestConfig.custom()    
                .setConnectionRequestTimeout(timeout)  
                .setConnectTimeout(timeout)    
                .setSocketTimeout(timeout).setRedirectsEnabled(false).build(); 
		return HttpClientBuilder.create().setSslcontext(sslContext).setConnectionManager(connMgr)
		  .setDefaultRequestConfig(requestConfig).build();
		}
 

//    private static  final BasicCookieStore cookieStore  ;
    
	
	
    static {	
    	/*
       RequestConfig requestConfig = RequestConfig.custom()    
                .setConnectionRequestTimeout(timeout)  
                .setConnectTimeout(timeout)    
                .setSocketTimeout(timeout).setRedirectsEnabled(false).build();  
       */
      /*   // cookie使锟斤拷没锟叫成癸拷 直锟斤拷锟斤拷header 锟斤拷锟�
        cookieStore = new BasicCookieStore();
    	BasicClientCookie cookie2 = new BasicClientCookie("PHPSESSID","dt9ije6vvdfvef7q20n9lch3h7");
    	BasicClientCookie cookie = new BasicClientCookie("token","b82c1388eb25b023b38f85eec218cee94079942457");
    	cookie.setDomain("daojia.com.cn");
    	cookie2.setDomain("daojia.com.cn");
    	cookieStore.addCookie(cookie);
    	cookieStore.addCookie(cookie2);

        httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(requestConfig).build();
     
      */
//        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
       
			try {
				httpClient = getIgnoreSslCertificateHttpClient();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }
 
 

    public static String doGetForm(String url, Map<String,String> params, String charset){
    	String result = "";
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
      
    }
    
    
    public static String doGetForm(String url, Header header, Map<String,String> params, String charset){
    	String result = "";
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Cookie", header.getValue());
            
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
      
    }
    
    
    
    
    public static CloseableHttpResponse doGetFormReq(String url, Header header, Map<String,String> params, String charset){
    	CloseableHttpResponse response = null;
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            System.out.println("Header Value: "+header.getValue());
            httpGet.addHeader("Cookie", header.getValue());
            
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 400) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            
            return response;
       
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * get 一锟斤拷锟斤拷锟矫碉拷url
     * @param url  http://xxx.com?xx=a&xx=c
     * @param params
     * @param charset
     * @return
     */
    public static String doGetStrReq(String url,String charset){
    	String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Cookie", "dtCookie=81B75BE571665F2F392B33F7E310B1DB|X2RlZmF1bHR8MQ");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (iPad; CPU OS 10_2 like Mac OS X) AppleWebKit/602.3.12 (KHTML, like Gecko) Mobile/14C92");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
      
    }
    
    
    public static CloseableHttpResponse doGetStrReqCode(String url,String charset){
    	CloseableHttpResponse response=null;
    	int retry=3;
    	int i=0;
    	boolean result=true;
     
        	while(i++ < retry && result)
        	{
            HttpGet httpGet = new HttpGet(url);
            try
            {
            	response = httpClient.execute(httpGet);
            	
            	result=false;
            }catch(Exception e)
            {
            	System.out.println("Retry for the: "+url+ " for "+i+"times.");
            	e.printStackTrace();
            	try {
					Thread.sleep(2000l);
				} catch (InterruptedException e1) {
					
				}
            }

        } 
        return response;
      
    }
    /**
     * post form锟斤拷锟斤拷锟斤拷式
     * @param url
     * @param postHeader
     * @param params
     * @param charset
     * @return
     */
    public static String doPostFormReq(String url,Map<String, String> postHeader, Map<String,String> params,String charset){
    	String result = "";
    	System.out.println(url);
        try {
        	 
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            
            HttpPost httpPost = new HttpPost(url);
            if(postHeader!=null)
            {
	            for(Map.Entry<String,String> entry : postHeader.entrySet()){
	             	String key = entry.getKey();
	             	String value = entry.getValue();
	             	httpPost.addHeader(key, value);
	             }
            }
            if(pairs != null && pairs.size() > 0){
                //httpPost.setEntity(new UrlEncodedFormEntity(pairs,charset));
                httpPost.setEntity(new UrlEncodedFormEntity(pairs));
                
            }
            CloseableHttpClient localHttpClient=getIgnoreSslCertificateHttpClient();
            //CloseableHttpResponse response = httpClient.execute(httpPost);
            CloseableHttpResponse response = localHttpClient.execute(httpPost);
           
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            
            if (entity != null){
            	System.out.println("Entity contains text.");
                result = EntityUtils.toString(entity, "utf-8");
                System.out.println("result: "+result);
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    public static Header getAuthorization(String url,Map<String, String> postHeader, Map<String,String> params,String charset){
    	Header header=null;
    	try {
        	 
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if(header!=null)
            {
	            for(Map.Entry<String,String> entry : postHeader.entrySet()){
	             	String key = entry.getKey();
	             	String value = entry.getValue();
	             	httpPost.addHeader(key, value);
	             }
            }
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            
            Header[] headers=response.getHeaders("Set-Cookie");
            int headerLen=headers.length;
            
            for(int j=headerLen-1;j>=0;j--)
            {
            	if(headers[j].getValue().contains("JSESSIONID"))
            	{
            		header=headers[j];
            		break;
            	}
            }
           
           
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        return header;
    }
    
    /**
     * post 锟较达拷锟侥硷拷
     * @param url
     * @param postHeader
     * @param params
     * @param charset
     * @return 
     */
    public static String doPostFormFile(String url,Map<String, String> postHeader, Map<String,String> params, String fileName, String filePath, String json,String charset){
    	String result = "";
    	File img_file = new File(filePath);
    	FileBody bin = new FileBody(img_file);
    	
    	MultipartEntityBuilder mb = MultipartEntityBuilder.create();
        try {

            if(params != null && !params.isEmpty()){
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                    	mb.addTextBody(entry.getKey(),value);
                    }
                }
            }
            mb.addPart(fileName, bin);
            for(int i=2;i<=12;i++)
            {
            	mb.addBinaryBody("uploadFile"+i,"".getBytes(),ContentType.APPLICATION_OCTET_STREAM,"");
            }
            mb.setBoundary("---------------------------7e01fa3680390");
            mb.addBinaryBody("reserveCase", json.getBytes(), ContentType.APPLICATION_JSON, "blob");
            HttpEntity reqEntity = mb.build();
            HttpPost httpPost = new HttpPost(url);
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
             	String key = entry.getKey();
             	String value = entry.getValue();
             	httpPost.addHeader(key, value);
             }
            
           
            httpPost.setEntity(reqEntity);
           
            System.out.println("Request: ");
            reqEntity.writeTo(System.out);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    
    
    /**
     * post 锟较达拷锟侥硷拷
     * @param url
     * @param postHeader
     * @param params
     * @param charset
     * @return 
     */
    public static String doPostRequest(String url,Map<String, String> postHeader, String params, String charset){
    	String result="";
    	try {
    		ContentProducer cp=new ContentProducer(){

				@Override
				public void writeTo(OutputStream out) throws IOException {
					// TODO Auto-generated method stub
					Writer writer=new OutputStreamWriter(out, "UTF-8");
					
					String requestXml=params;
					writer.write(requestXml);
					writer.flush();
				}
    			
    		};
    		
            HttpPost httpPost = new HttpPost(url);
            
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
             	String key = entry.getKey();
             	String value = entry.getValue();
             	httpPost.addHeader(key, value);
             }
            httpPost.setEntity(new EntityTemplate(cp));
            
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    //Post JSON data
    public static String doPostRequest(String url,Map<String, String> postHeader, Object json) throws HttpException, ClientProtocolException, IOException{
    	String result="";

    	HttpPost httpPost = new HttpPost(url);
            
        for(Map.Entry<String,String> entry : postHeader.entrySet()){
        	String key = entry.getKey();
            String value = entry.getValue();
            httpPost.addHeader(key, value);
         }

        CloseableHttpClient httpClient=getIgnoreSslCertificateHttpClient();
        
        StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�  
        stringEntity.setContentEncoding("UTF-8");  
        stringEntity.setContentType("application/json");  
        httpPost.setEntity(stringEntity);  
        
        CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode+" : "+EntityUtils.toString(response.getEntity(), "utf-8"));
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            
        return result;
    }
    
    
    
    
    
    /**
     * post 锟斤拷锟斤拷为str 锟斤拷式 锟斤拷header
     * @param url
     * @param postHeader
     * @param strRequest
     * @param charset
     * @return
     */
    public static String doPostStringReq(String url, Map<String, String> postHeader, String strRequest, String charset){
    	String result = "";
        try {
        	HttpPost httpPost = new HttpPost(url);

            StringEntity strEntity = new StringEntity(strRequest, charset);
        
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
            	String key = entry.getKey();
            	String value = entry.getValue();
            	httpPost.addHeader(key, value);
            }
            httpPost.setEntity(strEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * post 锟斤拷锟斤拷为str 没锟斤拷header
     * @param url
     * @param strRequest
     * @param charset
     * @return
     */
    
    
    public static String doPostStringReq(String url, String strRequest, String charset){
    	String result = "";
        try {
        	
			
			
        	HttpPost httpPost = new HttpPost(url);
            StringEntity strEntity = new StringEntity(strRequest, charset);
          
            httpPost.setEntity(strEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
    	String url = "https://www.baidu.com";
		System.out.println(HttpsUtil.doGetStrReq(url, "utf-8"));
	}
       
}