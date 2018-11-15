package org.tomas.myapi.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.RestClientDefinitionException;
import org.tomas.myapi.clients.HelloService;

@ApplicationScoped
public class ClientHelloService {
	private static Logger log = Logger.getLogger(ClientHelloService.class.getName());

	@Traced
	public String callMe(String text) {
		String response = null;
		HelloService remoteApi;
		try {
			remoteApi = RestClientBuilder.newBuilder()
			        .baseUrl(new URL("http://localhost:8081/test-data-service")).build(HelloService.class);
			response = remoteApi.hello(text);
		} catch (IllegalStateException | RestClientDefinitionException
				| MalformedURLException e) {
			
			log.info("KLAIDA su rest build: " + e.getLocalizedMessage());
			
		} catch (Exception e) {
			log.info("KLAIDA exception: " + e.getLocalizedMessage());
		}
		
		return response;
	}

}
