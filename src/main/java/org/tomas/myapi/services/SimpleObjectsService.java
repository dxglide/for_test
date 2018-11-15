package org.tomas.myapi.services;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.opentracing.Traced;
import org.tomas.myapi.objects.SimpleObject;

@ApplicationScoped
public class SimpleObjectsService {	
	private List<SimpleObject> objects = null;

    
	@PostConstruct
	private void init(){
		objects = new ArrayList<SimpleObject>();
		
		
		SimpleObject obj = new SimpleObject();
		obj.setId(0);
		obj.setText("Nulinis tekstas");
		objects.add(obj);
		
	}
	
	public int getSize() {
		if (objects != null) {
			return objects.size();
		} else {
			return 0;
		}
	}
	

	public List<SimpleObject> getAllSimpleObjects() {
		return objects;
	}
	

	public SimpleObject getSimpleObjectById(int id) {
		
		List<SimpleObject> foundList = objects.stream().filter(simpleobject -> simpleobject.getId() == id).collect(Collectors.toList());
		if (foundList != null && foundList.size() > 0) {
			return foundList.get(0);
		} else {
			return null;
		}
		
	}
	
	public boolean removeAllSimpleObjects() {
		if (objects != null && objects.size() == 0) {
			objects.clear();
		}
		return true; // somthing to return
	}
	
	@Traced
	public boolean addSimpleObject(SimpleObject obj){
//		String tempText =  updateTextWithHello(obj.getText());
//		obj.setText(tempText);
		objects.add(obj);
		return true; // in future to handle some errors
	}
	
//	private String updateTextWithHello(String text) throws IllegalStateException, RestClientDefinitionException, MalformedURLException {
//		
//		HelloService remoteApi = RestClientBuilder.newBuilder()
//	            .baseUrl(new URL("http://localhost:8081/test-data-service"))
//	            .build(HelloService.class);
//	      return remoteApi.hello(text);
//		
//	}
//	

}
