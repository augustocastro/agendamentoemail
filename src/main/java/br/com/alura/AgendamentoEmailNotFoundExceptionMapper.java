package br.com.alura;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.alura.dto.MensagemErroDto;
import br.com.alura.exception.AgendamentoEmailNotFoundException;

@Provider
public class AgendamentoEmailNotFoundExceptionMapper implements ExceptionMapper<AgendamentoEmailNotFoundException> {
	
	@Override
	public Response toResponse(AgendamentoEmailNotFoundException e) {
		return Response.status(Response.Status.NOT_FOUND).entity(MensagemErroDto.build(e.getMensagens())).build();
	}
}
