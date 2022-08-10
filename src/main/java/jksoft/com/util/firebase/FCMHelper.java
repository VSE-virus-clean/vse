package jksoft.com.util.firebase;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import jksoft.com.util.GsonUtils;
import jksoft.com.util.MultiUtil;

/**
 * Firebase Push Util 
 */
@Component("fcmHelper")
public class FCMHelper {

	private static HttpClient httpClient = null;
    
    @Value(value="#{global['firebase.api.url']}")
	private String firebaseApiUrl;
	
	@Value(value="#{global['firebase.api.key']}")
	private String firebaseApiServerkey;
	
	@Resource(name="multiUtil")
    protected MultiUtil multiUtil;
	
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	
//	"notification":{
//	      "title":"Portugal vs. Denmark",
//	      "body":"great match!"
//	    }
//    
//	"data":{
//	      "Nick" : "Mario",
//	      "body" : "great match!",
//	      "Room" : "PortugalVSDenmark"
//	    }
	
	public JsonObject sendFcmMessage2(Map<String, Object> data, Map<String, Object> notification, String token) throws IOException {
        HttpPost httpPost = new HttpPost(firebaseApiUrl);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "key=" + firebaseApiServerkey);

        Map<String, Object> notiMessage = new HashMap<String, Object>();
		notiMessage.put("to", token);
		notiMessage.put("priority", "high");
		notiMessage.put("content_available", true);
		notiMessage.put("notification", notification);
		notiMessage.put("data", data);
        
        StringEntity entity = new StringEntity(multiUtil.toJson(notiMessage), "UTF-8");
        httpPost.setEntity(entity);
        
        if(httpClient == null){
        	httpClient = HttpClientBuilder.create().build();
        }
        
        String response = (String) httpClient.execute(httpPost, new BasicResponseHandler());

        JsonObject resultObject = GsonUtils.stringToJsonObject(response);
        
        return resultObject;
    }
    
}
