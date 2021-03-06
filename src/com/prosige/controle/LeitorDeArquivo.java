package com.prosige.controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.prosige.dominio.ArquivoSPED;



public class LeitorDeArquivo {
	private static ArrayList<String[]> arquivoArray;

	public ArquivoSPED montaArquivoSPED(File file) throws IOException {
		ArquivoSPED arquivoSPED = new ArquivoSPED();

		arquivoArray = new ArrayList<String[]>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			if (file != null) {
				fileReader = new FileReader(file);
			} else {
				System.exit(0);
			}
			bufferedReader = new BufferedReader(fileReader);
			String linhaLida = "";
			while ((linhaLida = bufferedReader.readLine()) != null) {
				arquivoArray.add(linhaLida.replaceAll("^[|]|[|]$", "").split("[|]", -1));
			}
			fileReader.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				fileReader.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		arquivoSPED.setArrayArquivo(arquivoArray);
		arquivoSPED.setCaminhoDoArquivo(file.getParent());
		arquivoSPED.setNomeDoArquivo(file.getName());

		return arquivoSPED;
	}
}