package org.tomas.myapi;

import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilderException;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.rest.client.RestClientDefinitionException;
import org.tomas.myapi.objects.SimpleObject;
import org.tomas.myapi.services.ClientHelloService;
import org.tomas.myapi.services.SimpleObjectsService;



@ApplicationScoped
@Path("/testobjects")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ApiServiceEndpoint {
	private static Logger log = Logger.getLogger(ApiServiceEndpoint.class.getName());
	
	@Inject
	private SimpleObjectsService simpleObjectService;

	@Inject
	private ClientHelloService clientHelloService;
	
//	@Inject
//	@Metric
//	Counter allRequestCount;
//	@Inject
//	@Metric
//	Counter failedRequestCount;
//	@Inject
//	@Metric
//	Counter okRequestCount;
	
	

	
	
	@PostConstruct
	private void init() {
//		allRequestCount.inc(0);
//		failedRequestCount.inc(0);
//		okRequestCount.inc(0);
	}
	
	@GET
	@Metered
	public List<SimpleObject> getAll() {
//		allRequestCount.inc();
//		okRequestCount.inc();
		
		
		
		
		
		log.info("Logeris cia is default'o :)  BANDOM" + clientHelloService.callMe("vau")); 
		return simpleObjectService.getAllSimpleObjects();
	}
	
	@GET
	@Metered
	@Path("/{id}") 
	public SimpleObject getSimpleObjectById(@PathParam("id") int id) {
//		allRequestCount.inc();
//		okRequestCount.inc();
		return simpleObjectService.getSimpleObjectById(id);
	}
	
	@POST
	@Metered
	public Response addSimpleObject(SimpleObject object){
		if(getSimpleObjectById(object.getId()) != null) {

			return Response.status(Status.CONFLICT).header("x-reason", "Simple object exist").build();
		}

			if (simpleObjectService.addSimpleObject(object)) {

				return Response.created(UriBuilder.fromMethod(ApiServiceEndpoint.class, "getSimpleObjectById")
																.build(object.getId())).build();
				
			} else {

				return Response.serverError().build();
			}

		
	}
	
	@POST
	@Metered
	@Path("/removeall")
	public Response removeAllSimpleObject(){
//		allRequestCount.inc();
		
		if (simpleObjectService.removeAllSimpleObjects()) {
//			okRequestCount.inc();
			return Response.status(Status.OK).build();
		} else {
//			failedRequestCount.inc();
			return Response.serverError().build();
		}

	}

}
