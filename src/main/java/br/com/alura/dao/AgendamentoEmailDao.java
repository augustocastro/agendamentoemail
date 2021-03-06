package br.com.alura.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.exception.AgendamentoEmailNotFoundException;

@Stateless
public class AgendamentoEmailDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}

	public void excluirAgendamentoEmail(Long id) throws AgendamentoEmailNotFoundException {
		AgendamentoEmail agendamentoEmail = buscarAgendamentoEmailPorId(id);
		entityManager.remove(agendamentoEmail);
	}
	
	public void atualizarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.merge(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarAgendamentosEmail() {
		String jpql = "SELECT a FROM AgendamentoEmail a";
		return entityManager.createQuery(jpql, AgendamentoEmail.class).getResultList();
	}

	public AgendamentoEmail buscarAgendamentoEmailPorId(Long id) throws AgendamentoEmailNotFoundException {
		AgendamentoEmail agendamentoEmail = entityManager.find(AgendamentoEmail.class, id);
		
		return Optional.ofNullable(agendamentoEmail).orElseThrow(
				() -> new AgendamentoEmailNotFoundException(String.format("O registro id %s n�o foi encontrado", id)));
	}

	public List<AgendamentoEmail> listarAgendamentosEmailPorEmail(String email) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM AgendamentoEmail a ");
		jpql.append("WHERE a.email = :email AND a.enviado = false");

		TypedQuery<AgendamentoEmail> query = entityManager.createQuery(jpql.toString(), AgendamentoEmail.class);
		query.setParameter("email", email);

		return query.getResultList();
	}

	public List<AgendamentoEmail> buscarAgendamentosEmailNaoEnviados() {
		String jpql = "SELECT a FROM AgendamentoEmail a WHERE a.enviado = false";
		return entityManager.createQuery(jpql, AgendamentoEmail.class).getResultList();
	}

}
