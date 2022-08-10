package jksoft.com.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

/**
 * httpClient를 이용한 API 호출 
 */
@Component("httpClientHelper")
public class HttpClientHelper {

	private static HttpClient httpClient = null;
	
	public static final int DEFAULT_TIME_OUT = 3000;

	@Resource(name="multiUtil")
    protected MultiUtil multiUtil;
	
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	
	public JsonObject get(String requestUrl) throws IOException {
		
		JsonObject resultObject = new JsonObject();
		
		if(httpClient == null){
        	httpClient = HttpClientBuilder.create().build();
        }
		
		HttpGet httpGet = new HttpGet(requestUrl);
		
		HttpResponse response = httpClient.execute(httpGet);
		
		int iStatus = response.getStatusLine().getStatusCode();

		//Response 출력
		if (iStatus == 200) {
			ResponseHandler<String> handler = new BasicResponseHandler();
			
			String responseTxt = handler.handleResponse(response);
			
			resultObject = GsonUtils.stringToJsonObject(responseTxt);
		} 
		
		resultObject.addProperty("status", iStatus);
		
		return resultObject;
	}
	
	public JsonObject post(String requestUrl, Map<String,String> paramMap) throws IOException {
		
		JsonObject resultObject = new JsonObject();
		
        Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(10000);
        builder.setSocketTimeout(10000);
        builder.setStaleConnectionCheckEnabled(false);
        RequestConfig config = builder.build();
        
        ArrayList<NameValuePair> postParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry: paramMap.entrySet()) {
            postParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
		
		if(httpClient == null){
        	httpClient = HttpClientBuilder.create().build();
        }
		
		HttpPost httpPost = new HttpPost(requestUrl);
//		httpPost.setHeader("Accept", "application/x-www-form-urlencoded; charset=UTF-8");
//		httpPost.setHeader("Connection", "keep-alive");
//		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
        httpPost.setConfig(config);
		
        HttpResponse response = httpClient.execute(httpPost);
		int iStatus = response.getStatusLine().getStatusCode();

		//Response 출력
		if (iStatus == 200) {
			ResponseHandler<String> handler = new BasicResponseHandler();
			
			String responseTxt = handler.handleResponse(response);
			
			try {
				resultObject = GsonUtils.stringToJsonObject(responseTxt);
			}catch(IllegalStateException illegalStateException) {
				resultObject.addProperty("response", responseTxt);
			}
		} 
		
		resultObject.addProperty("status", iStatus);
		
		return resultObject;
	}
}
