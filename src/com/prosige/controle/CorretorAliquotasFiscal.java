package com.prosige.controle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.prosige.dominio.ArquivoSPED;
import com.prosige.dominio.PadraoDeTributacao;

public class CorretorAliquotasFiscal {

	private List<String[]> arrayArquivoOriginal;
	private List<String[]> arrayArquivoCorrigido;
	private String caminhoDoArquivo;
	private String nomeDoArquivo;

	public CorretorAliquotasFiscal(ArquivoSPED arquivoOriginal) {

		this.arrayArquivoOriginal = arquivoOriginal.getArrayArquivo();
		this.caminhoDoArquivo = arquivoOriginal.getCaminhoDoArquivo();
		this.nomeDoArquivo = arquivoOriginal.getNomeDoArquivo();

	}

	public ArquivoSPED corrigeArquivo() {

		ArquivoSPED arquivoCorrigido = new ArquivoSPED(nomeDoArquivo, caminhoDoArquivo);

		iniciaArquivo();
		corrigeBlocoC();
		corrigeBlocoH();

		arquivoCorrigido.setArrayArquivo(arrayArquivoCorrigido);

		return arquivoCorrigido;

	}

	private void iniciaArquivo() {
		arrayArquivoCorrigido = new ArrayList<String[]>();

		for (String[] strings : arrayArquivoOriginal) {
			if (strings[0].equals("C100"))
				break;
			arrayArquivoCorrigido.add(strings);

		}
	}

	public void corrigeBlocoC() {
		boolean primeiro = false;
		boolean podeAdicionar = false;

		List<String[]> nota = new ArrayList<>();
		List<String[]> Reg190Mais = new ArrayList<>();
		List<String[]> RegC500Mais = new ArrayList<>();

		Map<PadraoDeTributacao, String[]> mapBlocoC190 = new HashMap<PadraoDeTributacao, String[]>();

		for (String[] strings : arrayArquivoOriginal) {
			if (strings[0].equals("C100"))
				podeAdicionar = true;

			if (podeAdicionar) {

				if (strings[0].equals("C110")) {
					nota.add(strings);

				} else if (strings[0].equals("C113"))
					nota.add(strings);

				else if (strings[0].equals("C140")) {
					nota.add(strings);

				} else if (strings[0].equals("C141")) {
					nota.add(strings);

				}

				else if (strings[0].equals("C170")) {
					nota.add(corrigeC170(strings));

				} else if (strings[0].equals("C190")) {
					// System.out.println("pega c190");
					mapBlocoC190 = PadraoDeTributacao.adicionaRegistroC190(corrigeC190(strings), mapBlocoC190);
				} else if (strings[0].equals("C191")) {
					Reg190Mais.add(strings);

				} else if (strings[0].equals("C195")) {
					Reg190Mais.add(strings);

				} else if (strings[0].equals("C500")) {
					RegC500Mais.add(strings);

				} else if (strings[0].equals("C590")) {
					RegC500Mais.add(strings);
					System.out.println("dsfsdf");

				}

				else if (strings[0].equals("C100")) {

					arrayArquivoCorrigido.addAll(nota);
					arrayArquivoCorrigido.addAll(mapBlocoC190.values());
					arrayArquivoCorrigido.addAll(Reg190Mais);
					if (primeiro)
						corrigeC100(nota, mapBlocoC190.values());

					nota = new ArrayList<>();
					Reg190Mais = new ArrayList<String[]>();
					mapBlocoC190 = new HashMap<PadraoDeTributacao, String[]>();
					nota.add(strings);
					primeiro = true;
					// System.out.println("bota c190");

				}

				else if (strings[0].equals("C990")) {
					arrayArquivoCorrigido.addAll(nota);
					arrayArquivoCorrigido.addAll(mapBlocoC190.values());
					corrigeC100(nota, mapBlocoC190.values());
					arrayArquivoCorrigido.addAll(RegC500Mais);
					arrayArquivoCorrigido.add(strings);

					break;
				}
			}
		}
	}

	private void corrigeBlocoH() {
		boolean podeAdicionar = false;
		for (String[] strings : arrayArquivoOriginal) {
			if (strings[0].equals("D001"))
				podeAdicionar = true;

			if (podeAdicionar)
				arrayArquivoCorrigido.add(strings);

		}
	}

	private String[] corrigeC170(String[] linha) {

		String cstOriginal = linha[9];
		String cfopOriginal = linha[10];
		String aliqOriginal = linha[13];

		Map<PadraoDeTributacao, String[]> listaParametros = PadraoDeTributacao.montaPadrao(linha, cstOriginal,
				cfopOriginal, aliqOriginal);
		PadraoDeTributacao padraoParaTestar = new PadraoDeTributacao(cstOriginal, cfopOriginal, aliqOriginal);

		if (listaParametros.containsKey(padraoParaTestar)) {

			linha[9] = listaParametros.get(padraoParaTestar)[0];
			linha[10] = listaParametros.get(padraoParaTestar)[1];
			linha[13] = listaParametros.get(padraoParaTestar)[2];

			if (linha[9].equals("000"))
				linha[12] = linha[6];
			double baseIcms = Double.parseDouble(linha[12].replaceAll(",", "."));
			double totalIcmsCorrigido = baseIcms * (Double.parseDouble(linha[13].replaceAll(",", ".")) / 100);

			linha[14] = String.format("%.2f", totalIcmsCorrigido);

		}

		return linha;

	}

	private String[] corrigeC190(String[] linha) {

		String cstOriginal = linha[1];
		String cfopOriginal = linha[2];
		String aliqOriginal = linha[3];

		Map<PadraoDeTributacao, String[]> listaParametros = PadraoDeTributacao.montaPadrao(linha, cstOriginal,
				cfopOriginal, aliqOriginal);
		PadraoDeTributacao padraoParaTestar = new PadraoDeTributacao(cstOriginal, cfopOriginal, aliqOriginal);

		if (listaParametros.containsKey(padraoParaTestar)) {

			linha[1] = listaParametros.get(padraoParaTestar)[0];
			linha[2] = listaParametros.get(padraoParaTestar)[1];
			linha[3] = listaParametros.get(padraoParaTestar)[2];

			double baseIcms = Double.parseDouble(linha[5].replaceAll(",", "."));

			if (cstOriginal.equals("040")) {
				baseIcms = Double.parseDouble(linha[4].replaceAll(",", "."));
			}
			double totalIcmsCorrigido = baseIcms * (Double.parseDouble(linha[3].replaceAll(",", ".")) / 100);

			linha[5] = String.format("%.2f", baseIcms);

			linha[6] = String.format("%.2f", totalIcmsCorrigido);

		}

		return linha;

	}

	private void corrigeC100(List<String[]> nota, Collection<String[]> listC190) {

		double baseIcms = 0;
		double valorIcms = 0;

		for (String[] strings : listC190) {
			baseIcms = baseIcms + Double.parseDouble(strings[5].replaceAll(",", "."));
			valorIcms = valorIcms + Double.parseDouble(strings[6].replaceAll(",", "."));

			nota.get(0)[20] = String.format("%.2f", baseIcms);
			nota.get(0)[21] = String.format("%.2f", valorIcms);
		}

		// private corrigeC190 (String)

		// }}
	}
}