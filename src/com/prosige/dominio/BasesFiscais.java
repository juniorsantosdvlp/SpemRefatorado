package com.prosige.dominio;

public class BasesFiscais {
	private int cfop;
	private int cst;
	private double basePis;
	private double baseCofins;
	private double baseIcms;
	private int percentPis;
	private int percentCofins;
	private double valorPis;
	private double valorCofins;
	private double valorIcms;
	private int natReceita;

	public int getNatReceita() {
		return natReceita;
	}

	public void setNatReceita(int natReceita) {
		this.natReceita = natReceita;
	}

	public int getCfop() {
		return cfop;
	}

	public void setCfop(int cfop) {
		this.cfop = cfop;
	}

	public int getCst() {
		return cst;
	}

	public void setCst(int cst) {
		this.cst = cst;
	}

	public double getBasePis() {
		return basePis;
	}

	public void setBasePis(double basePis) {
		this.basePis = basePis;
	}

	public double getBaseCofins() {
		return baseCofins;
	}

	public void setBaseCofins(double baseCofins) {
		this.baseCofins = baseCofins;
	}

	public double getBaseIcms() {
		return baseIcms;
	}

	public void setBaseIcms(double baseIcms) {
		this.baseIcms = baseIcms;
	}

	public int getPercentPis() {
		return percentPis;
	}

	public void setPercentPis(int percentPis) {
		this.percentPis = percentPis;
	}

	public int getPercentCofins() {
		return percentCofins;
	}

	public void setPercentCofins(int percentCofins) {
		this.percentCofins = percentCofins;
	}

	public double getValorPis() {
		return valorPis;
	}

	public void setValorPis(double valorPis) {
		this.valorPis = valorPis;
	}

	public double getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(double valorCofins) {
		this.valorCofins = valorCofins;
	}

	public double getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(double valorIcms) {
		this.valorIcms = valorIcms;
	}

}
