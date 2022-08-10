package jksoft.com.util.firebase;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("firebasePushNotificationsService")
public class FirebasePushNotificationsService {

	@Value(value="#{global['firebase.api.url']}")
	private String firebaseApiUrl;
	
	@Value(value="#{global['firebase.api.key']}")
	private String firebaseApiServerkey;
    
	protected Logger log;
    
    @Async
    public CompletableFuture<String> send(HttpEntity<byte[]> request) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization",  "key=" + firebaseApiServerkey));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8 "));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(firebaseApiUrl, request, String.class);
        
        return CompletableFuture.completedFuture(firebaseResponse);
    }

}
