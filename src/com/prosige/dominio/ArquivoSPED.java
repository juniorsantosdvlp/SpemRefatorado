package com.prosige.dominio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArquivoSPED {
	private String nomeDoArquivo;
	private String caminhoDoArquivo;
	private StringBuilder sbArquivo;
	private List<String[]> arrayArquivo;

	public ArquivoSPED(String nomeDoArquivo, String caminhoDoArquivo) {

		this.nomeDoArquivo = nomeDoArquivo;
		this.caminhoDoArquivo = caminhoDoArquivo;
	}

	public ArquivoSPED() {
		super();
	}
	public List<String[]> getArrayArquivo() {
		return arrayArquivo;
	}

	public void setArrayArquivo(List<String[]> arrayArquivo) {
		this.arrayArquivo = arrayArquivo;
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public String getCaminhoDoArquivo() {
		return caminhoDoArquivo;
	}

	public void setCaminhoDoArquivo(String caminhoDoArquivo) {
		this.caminhoDoArquivo = caminhoDoArquivo;
	}

	public StringBuilder getSbArquivo() {
		return sbArquivo;
	}

	public void setSbArquivo(StringBuilder sbArquivo) {
		this.sbArquivo = sbArquivo;
	}

	public void salvarArquivo() {

		StringBuilder sbarquivo = new StringBuilder();
		for (String[] strings : arrayArquivo) {
			for (int i = 0; i < strings.length; i++) {
				sbarquivo.append("|");
				sbarquivo.append(strings[i]);
			}
			sbarquivo.append("|");
			sbarquivo.append("\r\n");
		}
		String arquivoString = sbarquivo.toString();

		File arquivo = new File(this.caminhoDoArquivo, "corrigido " + nomeDoArquivo);

		try {
			if (arquivo.exists()) {
				arquivo.delete();
			}

			FileWriter fileW = new FileWriter(arquivo);
			BufferedWriter fr = null;
			fr = new BufferedWriter(fileW);

			try {
				fr.write(arquivoString);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// Salva as Alterações
			try {
				fr.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Fecha a escrita no Arquivo
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// }
		} catch (IOException e3) {
			e3.printStackTrace();
		}

	}
}
