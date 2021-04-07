package com.orange.bootcamp.validation;

public class ErrorResponse {

	private String campo;
	private String erro;
	
	public ErrorResponse() {}
	
	public ErrorResponse(String campo, String mensagem) {
		this.campo = campo;
		this.erro = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
}
