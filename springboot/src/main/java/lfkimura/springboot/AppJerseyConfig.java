package lfkimura.springboot;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath(value = "app")
public class AppJerseyConfig extends ResourceConfig {

	public static final String RESOURCES = "lfkimura.springboot.rest";

	public AppJerseyConfig() {
		packages(RESOURCES);
	}

}
