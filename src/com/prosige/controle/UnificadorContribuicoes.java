package com.prosige.controle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import com.prosige.dominio.ArquivoSPED;
import com.prosige.dominio.Registro;

public class UnificadorContribuicoes {

	private List<String[]> arrayArquivoUnificado;
	private List<String[]> arrayArquivoMatriz;
	private List<String[]> arrayArquivoFilial;
	private String caminhoDoArquivo;
	private String nomeDoArquivo;

	public UnificadorContribuicoes(ArquivoSPED arquivoMatriz, ArquivoSPED arquivoFilial) {

		this.arrayArquivoMatriz = arquivoMatriz.getArrayArquivo();
		this.arrayArquivoFilial = arquivoFilial.getArrayArquivo();
		this.caminhoDoArquivo = arquivoMatriz.getCaminhoDoArquivo();
		this.nomeDoArquivo = arquivoMatriz.getNomeDoArquivo();

	}

	public ArquivoSPED unificaArquivo() {

		ArquivoSPED arquivoUnificado = new ArquivoSPED(nomeDoArquivo, caminhoDoArquivo);

		iniciaArquivoUnificado();
		unificaReg0150();
		unificaRegComClasse("0190");
		unificaReg0200();
		unificaRegComClasse("0400");
		unificaReg0450();
		arrayArquivoUnificado.add(arrayArquivoFilial.stream().filter(registro -> registro[0].equals("0140")).findFirst().get());
		unificaReg0500();
		unificaReg0990();
		unificaRegC();
		unificaRegF();
		unificaRegM();

		arquivoUnificado.setArrayArquivo(arrayArquivoUnificado);

		return arquivoUnificado;

	}

	/**
	 * Inicia o Arquivo Unificado escreve até o registro 0111, atualizando o valor
	 * do mesmo
	 */
	
	public void iniciaArquivoUnificado() {

		String[] reg0111Unificado = new String[6];

		double recBrutaMatrizTrib = 0;
		double recBrutaMatrizNaoTrib = 0;
		double recBrutaFilialTrib = 0;
		double recBrutaFilialNaoTrib = 0;

		arrayArquivoUnificado = new ArrayList<String[]>();

		// pega Registro 0111 da filial
		for (String[] strings : arrayArquivoFilial) {
			if (strings[0].equals("0111")) {

				recBrutaFilialTrib = Double.parseDouble(strings[1].replaceAll(",", "."));
				recBrutaFilialNaoTrib = Double.parseDouble(strings[2].replaceAll(",", "."));

				break;
			}
		}

		// pega o Registro 0111 da Matriz

		for (String[] strings : arrayArquivoMatriz) {

			if (strings[0].equals("0111")) {
				recBrutaMatrizTrib = Double.parseDouble(strings[1].replaceAll(",", "."));
				recBrutaMatrizNaoTrib = Double.parseDouble(strings[2].replaceAll(",", "."));
				break;

			}
			arrayArquivoUnificado.add(strings);
		}

		// Soma Registros Matriz com filial
		reg0111Unificado[0] = "0111";
		reg0111Unificado[1] = String.format("%.2f", recBrutaFilialTrib + recBrutaMatrizTrib);
		reg0111Unificado[2] = String.format("%.2f", recBrutaFilialNaoTrib + recBrutaMatrizNaoTrib);
		reg0111Unificado[3] = "0";
		reg0111Unificado[4] = "0";
		reg0111Unificado[5] = String.format("%.2f",
				+recBrutaFilialTrib + recBrutaMatrizTrib + recBrutaFilialNaoTrib + recBrutaMatrizNaoTrib);
		arrayArquivoUnificado.add(reg0111Unificado);
		arrayArquivoUnificado
				.add(arrayArquivoMatriz.stream().filter(registro -> registro[0].equals("0140")).findFirst().get());
	}

	public void unificaReg0990() {

		arrayArquivoUnificado.add(new String[] { "0990", Integer.toString(arrayArquivoUnificado.size() + 1) });
	}

	public void unificaReg0500() {

		LinkedHashMap<String, String[]> mapRegunificado = new LinkedHashMap<>();

		arrayArquivoMatriz.stream().filter(registro -> registro[0].equals("0500"))
				.forEach(reg -> mapRegunificado.put(reg[6], reg));

		arrayArquivoFilial.stream().filter(registro -> registro[0].equals("0500"))
				.forEach(reg -> mapRegunificado.put(reg[6], reg));

		arrayArquivoUnificado.addAll(mapRegunificado.values());

	}

	public void unificaReg0200() {

		LinkedHashMap<String, String[]> mapRegunificado = new LinkedHashMap<>();

		arrayArquivoMatriz.stream().filter(registro -> registro[0].equals("0200"))
				.forEach(reg -> mapRegunificado.put(reg[1], reg));

		arrayArquivoFilial.stream().filter(registro -> registro[0].equals("0200"))
				.forEach(reg -> mapRegunificado.put(reg[1], reg));

		arrayArquivoUnificado.addAll(mapRegunificado.values());

	}

	public void unificaReg0150() {

		LinkedHashMap<String, String[]> mapRegunificado = new LinkedHashMap<>();

		arrayArquivoMatriz.stream().filter(registro -> registro[0].equals("0150"))
				.forEach(reg -> mapRegunificado.put(reg[1], reg));

		arrayArquivoFilial.stream().filter(registro -> registro[0].equals("0150"))
				.forEach(reg -> mapRegunificado.put(reg[1], reg));

		arrayArquivoUnificado.addAll(mapRegunificado.values());

	}

	public void unificaRegC() {

		boolean podeAdicionar = false;
		int contador = 0;

		for (String[] strings : arrayArquivoMatriz) {

			if (strings[0].equals("A001"))
				podeAdicionar = true;

			if (strings[0].equals("C990"))
				podeAdicionar = false;

			if (podeAdicionar) {
				arrayArquivoUnificado.add(strings);
				contador++;
			}
		}

		podeAdicionar = false;
		for (String[] strings : arrayArquivoFilial) {

			if (strings[0].equals("C010"))
				podeAdicionar = true;

			if (strings[0].equals("C990"))
				podeAdicionar = false;

			if (podeAdicionar) {
				arrayArquivoUnificado.add(strings);
				contador++;
			}
		}

		arrayArquivoUnificado.add(new String[] { "C990", Integer.toString(contador - 1) });

	}

	public void unificaRegF() {

		boolean podeAdicionar = false;
		int contador = 0;

		for (String[] strings : arrayArquivoMatriz) {

			if (strings[0].equals("D001"))
				podeAdicionar = true;

			if (strings[0].equals("F990"))
				podeAdicionar = false;

			if (podeAdicionar) {
				arrayArquivoUnificado.add(strings);
				contador++;
			}

		}

		podeAdicionar = false;
		for (String[] strings : arrayArquivoFilial) {

			if (strings[0].equals("D010"))
				podeAdicionar = true;

			if (strings[0].equals("F990"))
				podeAdicionar = false;

			if (podeAdicionar) {
				arrayArquivoUnificado.add(strings);
				contador++;
			}
		}

		arrayArquivoUnificado.add(new String[] { "F990", Integer.toString(contador - 2) });

	}

	public void unificaReg0450() {

		long tamanho = arrayArquivoMatriz.stream().filter(registro -> registro[0].equals("0450")).count();

		arrayArquivoMatriz.stream().filter(registro -> registro[0].equals("0450"))
				.forEach(reg -> arrayArquivoUnificado.add(reg));
		;

		arrayArquivoFilial.stream().filter(registro -> registro[0].equals("0450")).forEach(reg -> {
			reg[1] = Long.toString((Integer.parseInt(reg[1]) + tamanho));
			arrayArquivoUnificado.add(reg);

		});

	}

	public void unificaRegComClasse(String regParaUnificar) {

		LinkedHashSet<Registro> registros = new LinkedHashSet<Registro>();

		arrayArquivoMatriz.stream().filter (registro -> registro[0].equals(regParaUnificar) )
				.forEach(reg -> registros.add(new Registro(reg)));

		arrayArquivoFilial.stream().filter(registro -> registro[0].equals(regParaUnificar))
				.forEach(reg -> registros.add(new Registro(reg)));

		registros.forEach(c -> arrayArquivoUnificado.add(c.getCampo()));

	}

	public void unificaRegM() {

		boolean podeAdicionar = false;

		for (String[] strings : arrayArquivoMatriz) {

			if (strings[0].equals("I001"))
				podeAdicionar = true;

			if (strings[0].equals("I001"))
				podeAdicionar = true;
			
			if (podeAdicionar) {
				// arrayArquivoUnificadoe.add(strings);
			}
		}

		arrayArquivoUnificado.get(arrayArquivoUnificado.size() - 1)[1] = Integer.toString(arrayArquivoUnificado.size());

	}
}