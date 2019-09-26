package com.prosige.controle.teste;

import java.awt.Cursor;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.prosige.controle.CorretorSPED;
import com.prosige.controle.LeitorDeArquivo;
import com.prosige.dominio.ArquivoSPED;
import com.prosige.visao.EscolhedorDeArquivo;

public class UnificaSPEDTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public UnificaSPEDTest() throws InterruptedException, IOException {

		setSize(400, 100);

		setLocationRelativeTo(null);
		setCursor(new Cursor(Cursor.WAIT_CURSOR));

		setTitle("Carregando");

		LeitorDeArquivo leitor = new LeitorDeArquivo();
		ArquivoSPED arquivo = new ArquivoSPED();
		CorretorSPED corretorSPED = new CorretorSPED();
		File file = EscolhedorDeArquivo.escolher();
		setVisible(true);

		arquivo = leitor.montaArquivoSPED(file);

		corretorSPED.corrigirArquivo(arquivo);

		arquivo.salvarArquivo();

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		setVisible(false);
		JOptionPane.showMessageDialog(this, "arquivo corrigido salvo em C:/Users/Junior/Desktop");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new UnificaSPEDTest();

	}
}