package br.com.alura;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.alura.dto.MensagemErroDto;
import br.com.alura.exception.BusinessException;

@Provider
public class EmailDuplicadoExceptionMapper implements ExceptionMapper<BusinessException> {
	
	@Override
	public Response toResponse(BusinessException e) {
		return Response.status(Response.Status.BAD_REQUEST).entity(MensagemErroDto.build(e.getMensagens())).build();
	}
}
