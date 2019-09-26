package com.prosige.controle.teste;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TestaGuava {
	public static void main(String[] args) {

		Table<String, String, String[]> tabela = HashBasedTable.create();

		tabela.put("40", "0", new String[] { "0", "7" });
		tabela.put("0", "19", new String[] { "0", "20" });
		//tabela.;
		
	}

}
