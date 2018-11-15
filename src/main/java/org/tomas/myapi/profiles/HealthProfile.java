package org.tomas.myapi.profiles;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class HealthProfile implements HealthCheck {

	@Override
	public HealthCheckResponse call() {

		return HealthCheckResponse.named("Mano api chekcas").up()
				.withData("Info1", "kazkokia reiksme")
				.withData("Info2", "antra reiksme").build();
	}

}
