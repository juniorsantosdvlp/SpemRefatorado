package com.prosige.controle;

import com.prosige.dominio.ArquivoSPED;

public class CorretorSPED {

	 private ArquivoSPED arquivoSPED = new ArquivoSPED();

	public void corrigirArquivo(ArquivoSPED arquivoSPED) {
		percorrerArquivo(arquivoSPED);

	}

	public String percorrerArquivo(ArquivoSPED arquivoSPED) {

		for (String[] strings : arquivoSPED.getArrayArquivo()) {

			trocaNome(strings);

		}
		return "";
	}
	//Passo 1
	
	public void InicializaArquivoUnificado (String[] strings) {
		while (!strings[0].equals("0111")) {
			arquivoSPED.getArrayArquivo().add(strings);
			
		}
		
	}
	
	public void Atualizar0111(String[] strings1,String[] strings2){
		
		//String[] reg0111 = new String[5] ;
		
		if(strings1[0].equals("0111")){
			
		}

	}

	private void trocaNome(String[] strings) {
		
		if (strings[0].equals("0000")) {
			strings[5] = "Junior";
		}
	}

}