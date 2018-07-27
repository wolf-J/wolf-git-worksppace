package util;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;  
import org.apache.http.conn.ssl.SSLContextBuilder;  
import org.apache.http.conn.ssl.TrustStrategy;  
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
  





import com.google.common.io.Files;

import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLException;  
import javax.net.ssl.SSLSession;  
import javax.net.ssl.SSLSocket;  

import java.io.File;
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;  
import java.security.GeneralSecurityException;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
/** 
 * HTTP 锟斤拷锟襟工撅拷锟斤拷 
 * 
 * @author : liii 
 * @version : 1.0.0 
 * @date : 2015/7/21 
 * @see : TODO 
 */  
public class HttpUtil {  
    private static PoolingHttpClientConnectionManager connMgr;  
    private static RequestConfig requestConfig;  
    private static final int MAX_TIMEOUT = 600000;  
  
    static {  
        // 锟斤拷锟斤拷锟斤拷锟接筹拷  
        connMgr = new PoolingHttpClientConnectionManager();  
        // 锟斤拷锟斤拷锟斤拷锟接池达拷小  
        connMgr.setMaxTotal(100);  
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());  
  
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(MAX_TIMEOUT);  
        configBuilder.setSocketTimeout(MAX_TIMEOUT);  
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);  
        configBuilder.setStaleConnectionCheckEnabled(true);  
        requestConfig = configBuilder.build();  
    }  
  
   
  
    
    /** 
     * 锟斤拷锟斤拷 POST 锟斤拷锟斤拷HTTP锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 
     * @param apiUrl 
     * @return 
     */  
    public static String doPost(String apiUrl) {  
        return doPost(apiUrl, new HashMap<String, Object>());  
    }  
  
    /** 
     * 锟斤拷锟斤拷 POST 锟斤拷锟斤拷HTTP锟斤拷锟斤拷K-V锟斤拷式 
     * @param apiUrl API锟接匡拷URL 
     * @param params 锟斤拷锟斤拷map 
     * @return 
     */  
    public static String doPost(String apiUrl, Map<String, Object> params) {  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            List<NameValuePair> pairList = new ArrayList<>(params.size());  
            for (Map.Entry<String, Object> entry : params.entrySet()) {  
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry  
                        .getValue().toString());  
                pairList.add(pair);  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));  
            response = httpClient.execute(httpPost);  
          
            HttpEntity entity = response.getEntity();  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return httpStr;  
    }  
    
    public static byte[] doGetStrReq(String url,String charset){

    	String result = "";
    	CloseableHttpClient httpClient = HttpClients.createDefault();  
    	byte[] bytes=null;
    	
        try {

            HttpGet httpGet = new HttpGet(url);
            //httpGet.addHeader("Authorization", "Basic cHJvZmVybzpwcm9mZXJv");   
           httpGet.addHeader("Cookie", "LtpaToken2=g0oaG2bXLJRssVQf8y3CJjKx1+pF6Hi3Fr/dZNrnEiwdwiEgjGuErDCa2jbjxTVA2T62d6Ka52SA7Qr6zzhUoQCkxd7QpSifcsvjCkUke0HhSGt78W1oHevAelY46LTpgNx0eV0CO0/wa1x99bzmZ7yjx3vi8lfmiIJFfQRqloY/Zx8cTWvTV0uVqPeBU92/97dnklxmvnbGHTXbb2ekQgKAkN/DOhYCPyydzliXN9qNfPvYY0ndwg/snM1dH2Ecq/XJcj8sHrOnCWS0xmScmLP5eHUsJINJGX9eMuBsvByibxAX7KObqJ0dKR+ioam6IqkDb36OIKlFz3X1WEpiGYGvpZXkaUj6m2ulAdNPPhKVAidjzqDTbi9ItLnJJ4nKvWirBhdIhbk0UOuaHWIdrKL1BwsmaToWZiP1G6d2tnERq9SIleG1Wp3ADwgUphEDPNIs6N3sFdzjgHZdUaAjcpaCpGrj1LHPoP6fHYIBi3I73GMH3c9UcQKtwmsIu9kmgsG2qC908ro8yF0klB3uTM/NiNeCZ9TxpvSAwEO8N2STWaJ3wb9+arTuRXpjp+NwYK6gHzKXcCy3wpYuku0/DXcc9FmSyomCof7PbA0ob6QVhM3CfKRSf5YkjBoyTzz4yuZlApPOeyajpvrKaXr2BTHUZ0pq2KWgcCKOP3ZWzFo=");
           
            
            CloseableHttpResponse response = httpClient.execute(httpGet);
            
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode > 400) {

                httpGet.abort();

                throw new RuntimeException("HttpClient,error status code :" + statusCode);

            }

            HttpEntity entity = response.getEntity();
            
            bytes=EntityUtils.toByteArray(entity);
            
            System.out.println("Content Length: "+bytes.length);
          
            
            

            EntityUtils.consume(entity);

            response.close();

           

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bytes;

    }
    
    public static String doFormPost(String apiUrl, Map<String,String> header, Map<String, String> params) {  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
        
        httpPost.addHeader("Cookie", header.get("Cookie"));
        try {  
            httpPost.setConfig(requestConfig);  
            List<NameValuePair> pairList = new ArrayList<>(params.size());  
            for (Map.Entry<String, String> entry : params.entrySet()) {  
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry  
                        .getValue().toString());  
                pairList.add(pair);  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));  
            response = httpClient.execute(httpPost);  
          
            HttpEntity entity = response.getEntity();  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return httpStr;  
    }  
    
    public static String doPost(String apiUrl, Map<String,String> header, Map<String, Object> params) {  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
  
        header.entrySet().stream().forEach(entry -> httpPost.addHeader(entry.getKey(), entry.getValue()));
        
        try {  
            httpPost.setConfig(requestConfig);  
            List<NameValuePair> pairList = new ArrayList<>(params.size());  
            for (Map.Entry<String, Object> entry : params.entrySet()) {  
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry  
                        .getValue().toString());  
                pairList.add(pair);  
            }  
            
            
            
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));  
            response = httpClient.execute(httpPost);  
          
            HttpEntity entity = response.getEntity();  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return httpStr;  
    }  
  
    /** 
     * 锟斤拷锟斤拷 POST 锟斤拷锟斤拷HTTP锟斤拷锟斤拷JSON锟斤拷式 
     * @param apiUrl 
     * @param json json锟斤拷锟斤拷 
     * @return 
     */  
    public static String doPost(String apiUrl, Map<String,String> header,Object json) {  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
        
        System.out.println("Request URL: "+apiUrl);
        header.entrySet().stream().forEach(entry -> httpPost.addHeader(entry.getKey(), entry.getValue()));
        
        try {  
            httpPost.setConfig(requestConfig);  
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");
            stringEntity.setContentEncoding("UTF-8");  
            stringEntity.setContentType("application/json");  
            httpPost.setEntity(stringEntity);  
            
            response = httpClient.execute(httpPost);  
            HttpEntity entity = response.getEntity();  
            System.out.println(response.getStatusLine().getStatusCode());  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return httpStr;  
    }  
  
    /** 
     * 锟斤拷锟斤拷 SSL POST 锟斤拷锟斤拷HTTPS锟斤拷锟斤拷K-V锟斤拷式 
     * @param apiUrl API锟接匡拷URL 
     * @param params 锟斤拷锟斤拷map 
     * @return 
     */  
    public static String doPostSSL(String apiUrl, Map<String, Object> params) {  
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
        String httpStr = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());  
            for (Map.Entry<String, Object> entry : params.entrySet()) {  
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry  
                        .getValue().toString());  
                pairList.add(pair);  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));  
            response = httpClient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
                return null;  
            }  
            HttpEntity entity = response.getEntity();  
            if (entity == null) {  
                return null;  
            }  
            httpStr = EntityUtils.toString(entity, "utf-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return httpStr;  
    }  
  
    /** 
     * 锟斤拷锟斤拷 SSL POST 锟斤拷锟斤拷HTTPS锟斤拷锟斤拷JSON锟斤拷式 
     * @param apiUrl API锟接匡拷URL 
     * @param json JSON锟斤拷锟斤拷 
     * @return 
     */  
    public static String doPostSSL(String apiUrl, Object json) {  
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
        String httpStr = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�  
            stringEntity.setContentEncoding("UTF-8");  
            //stringEntity.setContentType("application/json");  
            httpPost.setEntity(stringEntity);  
            response = httpClient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
                return null;  
            }  
            HttpEntity entity = response.getEntity();  
            if (entity == null) {  
                return null;  
            }  
            httpStr = EntityUtils.toString(entity, "utf-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return httpStr;  
    }  
  
    /** 
     * 锟斤拷锟斤拷SSL锟斤拷全锟斤拷锟斤拷 
     * 
     * @return 
     */  
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {  
        SSLConnectionSocketFactory sslsf = null;  
        try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {  
  
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
                    return true;  
                }  
            }).build();  
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {  
  
                @Override  
                public boolean verify(String arg0, SSLSession arg1) {  
                    return true;  
                }  
  
                @Override  
                public void verify(String host, SSLSocket ssl) throws IOException {  
                }  
  
                @Override  
                public void verify(String host, X509Certificate cert) throws SSLException {  
                }  
  
                @Override  
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {  
                }  
            });  
        } catch (GeneralSecurityException e) {  
            e.printStackTrace();  
        }  
        return sslsf;  
    }  
  
  
    
    public static String doPostRequest(String url,Map<String, String> postHeader, String params, String charset){
    	CloseableHttpClient client = HttpClients.createDefault();  
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
            httpPost.setConfig(requestConfig);
            
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
             	String key = entry.getKey();
             	String value = entry.getValue();
             	httpPost.addHeader(key, value);
             }
            httpPost.setEntity(new EntityTemplate(cp));
            
            CloseableHttpResponse response = client.execute(httpPost);
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
    
    
    
    public static CloseableHttpResponse doPostFormRequest(String url, Map<String,String> params,String charset){
    	CloseableHttpClient httpClient = HttpClients.createDefault();  
    	
    	String result = "";
    	CloseableHttpResponse response=null;

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
            
           httpPost.setConfig(requestConfig);

            if(pairs != null && pairs.size() > 0){

                httpPost.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            }
            
             response = httpClient.execute(httpPost);
            
         
            

        } catch (Exception e) {

            e.printStackTrace();

        }

        
        return response;
        

    }
    
    public static Header getAuthorization(String url,Map<String, String> postHeader, Map<String,String> params,String charset){
    	CloseableHttpClient httpClient = HttpClients.createDefault();  
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
            for(Map.Entry<String,String> entry : postHeader.entrySet()){
             	String key = entry.getKey();
             	String value = entry.getValue();
             	httpPost.addHeader(key, value);
             }
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,charset));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            header=response.getLastHeader("Set-Cookie");
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        return header;
    }
    
    /** 
     * 锟斤拷锟皆凤拷锟斤拷 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
    	byte[] result=doGetStrReq("http://thadculwce02:29082/sonora/FileStore?op=view&id=189007&version=1","UTF-8");
    	System.out.println(result.length);
    	Files.write(result, new File("C:/test.pdf"));
    }  

}