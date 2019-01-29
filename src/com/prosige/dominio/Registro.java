package com.prosige.dominio;

import java.util.Arrays;

public class Registro {
	
	private String[] campo;
	
	

	public Registro(String[] reg) {
	this.campo = reg;
	}

	public String[] getCampo() {
		return campo;
	}

	public void setCampo(String[] campo) {
		this.campo = campo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(campo);
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
		Registro other = (Registro) obj;
		if (!Arrays.equals(campo, other.campo))
			return false;
		return true;
	}

	
	

}
