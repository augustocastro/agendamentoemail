package br.com.alura.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.exception.AgendamentoEmailNotFoundException;
import br.com.alura.exception.BusinessException;
import br.com.alura.interception.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Logger
@Api("agendamentoemail")
@Path("/agendamentoemail")
public class AgendamentoEmailResource {

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@ApiOperation(value = "Agendamentos Realizados", produces = MediaType.APPLICATION_JSON)
	@ApiResponses(@ApiResponse(code = 200, message = "success", response = AgendamentoEmail.class))
	@GET
	public Response listarAgendamentosEmail() {
		List<AgendamentoEmail> listaAgendamentos = agendamentoEmailBusiness.listarAgendamentosEmail();
		return Response.ok(listaAgendamentos).build();
	}

	@ApiOperation(value = "Cadastrar Agendamento", consumes = MediaType.APPLICATION_JSON)
	@ApiResponses(@ApiResponse(code = 201, message = "created"))
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarAgendamentoEmail(
			@ApiParam(value = "Agendamento Email", name = "agendamentoEmail", required = true) AgendamentoEmail agendamentoEmail)
			throws BusinessException {
		agendamentoEmailBusiness.salvarAgendamentoEmail(agendamentoEmail);
		return Response.status(201).build();
	}
	
	@ApiOperation(value = "Excluir Agendamento")
	@ApiResponses(@ApiResponse(code = 200, message = "deleted"))
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirAgendamentoEmail(
			@ApiParam(name = "id", required = true) @PathParam("id") Long id)
			throws BusinessException, AgendamentoEmailNotFoundException {
		agendamentoEmailBusiness.excluirAgendamentoEmail(id);
		return Response.status(200).build();
	}

}
