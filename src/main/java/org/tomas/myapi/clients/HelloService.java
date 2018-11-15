package org.tomas.myapi.clients;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@RegisterRestClient
@RequestScoped
public interface HelloService {

	@Path("{name}")
	@GET
	String hello(@PathParam("name") String name);

	@Path("{name}")
	@GET
	CompletionStage<String> helloAsync(@PathParam("name") String name);

}
