package org.tomas.myapi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.tomas.myapi.services.SimpleObjectsService;

@ApplicationScoped
@Path("/apiusage")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UsageStatisticEndpoint {

//	@Inject
//	private SimpleObjectsService simpleObjectService;
//	
//	@usage
	
}
