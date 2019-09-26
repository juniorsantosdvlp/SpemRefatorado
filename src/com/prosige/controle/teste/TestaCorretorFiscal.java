package com.prosige.controle.teste;

import java.io.File;
import java.io.IOException;
import com.prosige.controle.CorretorAliquotasFiscal;
import com.prosige.controle.LeitorDeArquivo;
import com.prosige.dominio.ArquivoSPED;
import com.prosige.visao.EscolhedorDeArquivo;

public class TestaCorretorFiscal {

	public static void main(String[] args) throws IOException {

		LeitorDeArquivo leitor = new LeitorDeArquivo();
		ArquivoSPED arquivo = new ArquivoSPED();
		ArquivoSPED arquivoCorrigido = new ArquivoSPED();
		;

		arquivo = leitor
				.montaArquivoSPED(EscolhedorDeArquivo.escolher());

		CorretorAliquotasFiscal corretor = new CorretorAliquotasFiscal(arquivo);
		arquivoCorrigido = corretor.corrigeArquivo();
		arquivoCorrigido.salvarArquivo();


	}
}