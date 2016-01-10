package lfkimura.springboot.rest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import lfkimura.springboot.services.AppService;

@Path("queue")
public class AppResource extends ResourceConfig {

	public AppResource() {
		packages("lfkimura.springboot.rest");
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHi() {
		return "Hello app";
	}

	@Autowired
	AppService service;

	@POST
	@Path("/enqueue")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void enqueueMessage(@Suspended final AsyncResponse asyncResponse, @NotNull String req)
			throws Exception {
		boolean resp = service.pushNotification(req);
		asyncResponse.resume(resp);

	}

}
