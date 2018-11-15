package org.tomas.myapi.profiles;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

@Health
@ApplicationScoped
public class HealthProfile2 implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		
		HealthCheckResponseBuilder healthBuilder = HealthCheckResponse.named("JAVA Antras checkas su kazkokiom reiksmem");
		Map<String, String> envMap = System.getenv();
		if (envMap != null) {
			healthBuilder.up();
			envMap.forEach((k,v)->healthBuilder.withData(k,v));
		} else {
			healthBuilder.down();
			healthBuilder.withData("BLOGAI ???", "Is cia nieko negavau System.getenv()");
		}
		return healthBuilder.build();
	}

}
