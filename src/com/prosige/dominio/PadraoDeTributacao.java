package com.prosige.dominio;

import java.util.HashMap;
import java.util.Map;

public class PadraoDeTributacao {

	private String CST;
	private String CFOP;
	private String aliquota;

	public PadraoDeTributacao() {
		super();
	}

	public PadraoDeTributacao(String cST, String cFOP, String aliquota) {
		super();
		CFOP = cFOP;
		this.aliquota = aliquota;
		CST = cST;
	}

	public static Map<PadraoDeTributacao, String[]> montaPadrao(String[] linha, String cstOriginal, String cfopOriginal,String aliqOriginal) {
		Map<PadraoDeTributacao, String[]> listaParametros = new HashMap<PadraoDeTributacao, String[]>();

	
	    listaParametros.put(new PadraoDeTributacao ("040",cfopOriginal,aliqOriginal), new String[] {"000",cfopOriginal,"7,00"}); //tudo que é isento vira tributado 7% aliq
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"13"), new String[] {cstOriginal,cfopOriginal,"20,00"}); 

	
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"8"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"27"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"26"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"18"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"19"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"19,00"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"13,00"), new String[] {cstOriginal,cfopOriginal,"20,00"}); 
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"8,00"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"27,00"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"26,00"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	    listaParametros.put(new PadraoDeTributacao (cstOriginal,cfopOriginal,"18,00"), new String[] {cstOriginal,cfopOriginal,"20,00"});
	
	    return listaParametros;
	}

	public static Map<PadraoDeTributacao ,String[]> adicionaRegistroC190(String[] strings,
			Map<PadraoDeTributacao, String[]> mapBlocoC190) {
		PadraoDeTributacao padraoTribOriginal = new PadraoDeTributacao(strings[1], strings[2], strings[3]);

		if (!mapBlocoC190.containsKey(padraoTribOriginal))
			mapBlocoC190.put(padraoTribOriginal, strings);
		else {
			mapBlocoC190.get(padraoTribOriginal)[4] =	String.format("%.2f",Double.parseDouble(mapBlocoC190.get(padraoTribOriginal)[4].replaceAll(",", ".")) + Double.parseDouble(strings[4].replaceAll(",", "."))) ;
			mapBlocoC190.get(padraoTribOriginal)[5] =	String.format("%.2f",Double.parseDouble(mapBlocoC190.get(padraoTribOriginal)[5].replaceAll(",", ".")) + Double.parseDouble(strings[5].replaceAll(",", "."))) ;
			mapBlocoC190.get(padraoTribOriginal)[6] =	String.format("%.2f",Double.parseDouble(mapBlocoC190.get(padraoTribOriginal)[6].replaceAll(",", ".")) + Double.parseDouble(strings[6].replaceAll(",", "."))) ;


			
		}			// 			double totalIcmsCorrigido = baseIcms * (Double.parseDouble(linha[3].replaceAll(",", ".")) / 100);
		// 			linha[14] = String.format("%.2f", totalIcmsCorrigido);

 
			return mapBlocoC190;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CFOP == null) ? 0 : CFOP.hashCode());
		result = prime * result + ((CST == null) ? 0 : CST.hashCode());
		result = prime * result + ((aliquota == null) ? 0 : aliquota.hashCode());
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
		PadraoDeTributacao other = (PadraoDeTributacao) obj;
		if (CFOP == null) {
			if (other.CFOP != null)
				return false;
		} else if (!CFOP.equals(other.CFOP))
			return false;
		if (CST == null) {
			if (other.CST != null)
				return false;
		} else if (!CST.equals(other.CST))
			return false;
		if (aliquota == null) {
			if (other.aliquota != null)
				return false;
		} else if (!aliquota.equals(other.aliquota))
			return false;
		return true;
	}

}