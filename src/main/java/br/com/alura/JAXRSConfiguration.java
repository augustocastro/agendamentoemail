
package br.com.alura;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.alura.resource.AgendamentoEmailResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/resources")
public class JAXRSConfiguration extends Application {
	
	public JAXRSConfiguration() {
		BeanConfig conf = new BeanConfig();
	    conf.setTitle("Agendamento Email API");
	    conf.setVersion("1.0.0");
	    conf.setHost("localhost:8080");
	    conf.setBasePath("/resources/");
	    conf.setSchemes(new String[] { "http" });
	    conf.setResourcePackage("br.com.alura");
	    conf.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(ConstraintValidationMapper.class);
		resources.add(EmailDuplicadoExceptionMapper.class);
		resources.add(AgendamentoEmailNotFoundExceptionMapper.class);
		resources.add(AgendamentoEmailResource.class);
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);
		return resources;
	}
	
}