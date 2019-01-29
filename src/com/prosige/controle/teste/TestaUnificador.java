package com.prosige.controle.teste;

import java.io.File;
import java.io.IOException;

import com.prosige.controle.LeitorDeArquivo;
import com.prosige.controle.UnificadorContribuicoes;
import com.prosige.dominio.ArquivoSPED;

public class TestaUnificador {
	public static void main(String[] args) throws IOException {
		// importa arquivos
		
		LeitorDeArquivo leitor = new LeitorDeArquivo();

		ArquivoSPED arquivoMatriz = new ArquivoSPED();
		ArquivoSPED arquivoFilial = new ArquivoSPED();
		ArquivoSPED arquivoUnificado = new ArquivoSPED();
		
		arquivoMatriz = leitor.montaArquivoSPED(new File("C:/Users/Junior/Desktop/TesteSPEM/arquivo matriz.txt"));
		arquivoFilial = leitor.montaArquivoSPED(new File("C:/Users/Junior/Desktop//TesteSPEM/arquivo filial.txt"));

		UnificadorContribuicoes unificador = new UnificadorContribuicoes(arquivoMatriz, arquivoFilial);
		
		arquivoUnificado = unificador.unificaArquivo();
		arquivoUnificado.salvarArquivo();
		
		
		

		// List<String[]> lista = new ArrayList<>();
		// arquivoMatriz.getArrayArquivo().stream().filter(c ->
		// c[0].equals("0111")).forEach(c -> lista.add(c));

	}

}
