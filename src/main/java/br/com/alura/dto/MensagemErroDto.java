package br.com.alura.dto;

import java.time.LocalDate;
import java.util.List;

public class MensagemErroDto {

	private List<String> mensagens;
	private LocalDate dataErro;

	public List<String> getMensagens() {
		return mensagens;
	}

	public LocalDate getDataErro() {
		return dataErro;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public void setDataErro(LocalDate dataErro) {
		this.dataErro = dataErro;
	}

	public static MensagemErroDto build(List<String> mensagens) {
		MensagemErroDto mensagemErroDto = new MensagemErroDto();
		mensagemErroDto.setMensagens(mensagens);
		mensagemErroDto.setDataErro(LocalDate.now());
		return mensagemErroDto;
	}
	
}
