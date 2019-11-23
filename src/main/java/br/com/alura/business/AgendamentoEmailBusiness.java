package br.com.alura.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.alura.dao.AgendamentoEmailDao;
import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.exception.AgendamentoEmailNotFoundException;
import br.com.alura.exception.BusinessException;

@Stateless
public class AgendamentoEmailBusiness {

	@Inject
	private AgendamentoEmailDao agendamentoEmailDao;

	public List<AgendamentoEmail> listarAgendamentosEmail() {
		return agendamentoEmailDao.listarAgendamentosEmail();
	}

	public void salvarAgendamentoEmail(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {
		List<AgendamentoEmail> agendamentosEmail = agendamentoEmailDao
				.listarAgendamentosEmailPorEmail(agendamentoEmail.getEmail());
		
		if (!agendamentosEmail.isEmpty()) {			
			throw new BusinessException("Já foi feito o agendamento com este email.");
		}
		
		agendamentoEmail.setEnviado(false);
		agendamentoEmailDao.salvarAgendamentoEmail(agendamentoEmail);
	}

	public void excluirAgendamentoEmail(Long id) throws AgendamentoEmailNotFoundException {
		agendamentoEmailDao.excluirAgendamentoEmail(id);
	}

}
