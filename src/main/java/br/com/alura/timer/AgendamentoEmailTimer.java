package br.com.alura.timer;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entity.AgendamentoEmail;

@Singleton
public class AgendamentoEmailTimer {

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@Schedule(hour = "*", minute = "*")
	public void enviarEmailsAgendados() {
		List<AgendamentoEmail> agendamentoEmails = agendamentoEmailBusiness.buscarAgendamentosEmailNaoEnviados();
		
		agendamentoEmails.forEach(agendamentoEmail -> {
			context.createProducer().send(queue, agendamentoEmail);
			agendamentoEmailBusiness.marcarAgendamentoEmailComoEnviado(agendamentoEmail);
		});
	}

}
