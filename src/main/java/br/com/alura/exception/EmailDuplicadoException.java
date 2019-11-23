package br.com.alura.exception;

import java.util.ArrayList;
import java.util.List;

public class EmailDuplicadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> mensagens;

	public EmailDuplicadoException() {
		this.mensagens = new ArrayList<String>();
	}

	public EmailDuplicadoException(String mensagem) {
		super(mensagem);
		this.mensagens = new ArrayList<String>();
		this.mensagens.add(mensagem);
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void addMensagem(String mensagem) {
		this.mensagens.add(mensagem);
	}

}
