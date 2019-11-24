package br.com.alura.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class AgendamentoEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "{agendamentoEmail.email.vazio}")
	@Email(message = "{agendamentoEmail.email.invalido}")
	private String email;

	@NotBlank(message = "{agendamentoEmail.mensagem.vazio}")
	private String mensagem;

	@NotBlank(message = "{agendamentoEmail.assunto.vazio}")
	private String assunto;

	private Boolean enviado;

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgendamentoEmail other = (AgendamentoEmail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
