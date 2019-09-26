package com.prosige.dominio;

import java.util.ArrayList;

public class Nota {

	private String operacao;
	private String cnpjEmitente;
	private String cnpjFornecedor;
	private String chaveNFE;
	private int numeroNFE;
	private String serieNFE;
	private ArrayList<Produto> itensNFE;
	private BasesFiscais basesFiscais;

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getCnpjEmitente() {
		return cnpjEmitente;
	}

	public void setCnpjEmitente(String cnpjEmitente) {
		this.cnpjEmitente = cnpjEmitente;
	}

	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}

	public void setCnpjFornecedor(String cnpjFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
	}

	public String getChaveNFE() {
		return chaveNFE;
	}

	public void setChaveNFE(String chaveNFE) {
		this.chaveNFE = chaveNFE;
	}

	public int getNumeroNFE() {
		return numeroNFE;
	}

	public void setNumeroNFE(int numeroNFE) {
		this.numeroNFE = numeroNFE;
	}

	public String getSerieNFE() {
		return serieNFE;
	}

	public void setSerieNFE(String serieNFE) {
		this.serieNFE = serieNFE;
	}

	public ArrayList<Produto> getItensNFE() {
		return itensNFE;
	}

	public void setItensNFE(ArrayList<Produto> itensNFE) {
		this.itensNFE = itensNFE;
	}

	public BasesFiscais getBasesFiscais() {
		return basesFiscais;
	}

	public void setBasesFiscais(BasesFiscais basesFiscais) {
		this.basesFiscais = basesFiscais;
	}

}