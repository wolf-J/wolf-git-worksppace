package wolf_j.com.github.tdd.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

public class TimerExecutorTest {

	@Test
	void testClockbyhttpRequest() {
		TimerExecutorTest localTimerExecutor = new TimerExecutorTest();

		NTCredentials localNTCredentials = new NTCredentials("asnphm6", "hj96569120...", "CNCSPWDOM02.AIA.BIZ", "AIA");

		HttpResponse localHttpResponse = localTimerExecutor
				.request("http://cigp3r8cweb01.aia.biz/tcs/clock_checkrec.asp", null, localNTCredentials);

		String cookies = null;
		for (Header header : localHttpResponse.getAllHeaders()) {
			if (header.getName().equals("Set-Cookie")) {
				cookies = header.getValue();
			}
		}
		HttpResponse httpResponse = localTimerExecutor.request("http://cigp3r8cweb01.aia.biz/tcs/clock_logrec.asp", cookies,
				localNTCredentials);

		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
	}

	public HttpResponse request(String username, String password, NTCredentials paramNTCredentials) {
		BasicCredentialsProvider localBasicCredentialsProvider = new BasicCredentialsProvider();
		localBasicCredentialsProvider.setCredentials(AuthScope.ANY, paramNTCredentials);
		ArrayList<String> localArrayList1 = new ArrayList<String>();
		localArrayList1.add("NTLM");
		RequestConfig.Builder localBuilder = RequestConfig.custom();
		localBuilder.setTargetPreferredAuthSchemes(localArrayList1);

		CloseableHttpClient localCloseableHttpClient = HttpClientBuilder.create()
				.setDefaultRequestConfig(localBuilder.build())
				.setDefaultCredentialsProvider(localBasicCredentialsProvider).build();
		try {
			Object httpPost;
			if (password == null) {
				httpPost = new HttpGet(username);
			} else {
				httpPost = new HttpPost(username);
				((HttpPost) httpPost).addHeader("Cookie", password);
				((HttpPost) httpPost).addHeader("Content-Type", "application/x-www-form-urlencoded");

				ArrayList<BasicNameValuePair> localArrayList2 = new ArrayList<>();
				HashMap<String, String> localHashMap = new HashMap<>();
				localHashMap.put("Tuserid", paramNTCredentials.getUserName());
				localHashMap.put("B1", "Check");
				Iterator<?> localIterator = localHashMap.entrySet().iterator();
				Object localObject2;
				while (localIterator.hasNext()) {
					localObject2 = (Map.Entry) localIterator.next();
					localArrayList2.add(new BasicNameValuePair((String) ((Map.Entry) localObject2).getKey(),
							(String) ((Map.Entry) localObject2).getValue()));
				}
				if (localArrayList2.size() > 0) {
					localObject2 = new UrlEncodedFormEntity(localArrayList2, "utf-8");
					((HttpPost) httpPost).setEntity((HttpEntity) localObject2);
				}
			}
			return localCloseableHttpClient.execute((HttpUriRequest) httpPost);
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return null;
	}

}