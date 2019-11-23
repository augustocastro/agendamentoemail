package br.com.alura.interception;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

import br.com.alura.resource.AgendamentoEmailResource;

@Interceptor
@br.com.alura.interception.Logger
@Priority(1)
public class LoggerInterceptor {

	private Logger logger = Logger.getLogger(AgendamentoEmailResource.class.getName());

	@AroundInvoke
	public Object treatException(InvocationContext context) throws Exception {
		try {
			return context.proceed();
		} catch (EJBException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				logger.info(e.getMessage());
			} else {
				logger.severe(e.getMessage());
			}
			throw e;
		}
	}
	
}
