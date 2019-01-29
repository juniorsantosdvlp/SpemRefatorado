package com.prosige.controle.teste;


import java.awt.Cursor;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import com.prosige.controle.CorretorSPED;
import com.prosige.controle.LeitorDeArquivo;
import com.prosige.dominio.ArquivoSPED;
import com.prosige.visao.EscolhedorDeArquivo;



public class Programa extends JFrame {

	private static final long serialVersionUID = 1L;

	public Programa() throws InterruptedException, IOException {

		JProgressBar progresso = new JProgressBar();
		setSize(400, 100);
		add(progresso);
		setLocationRelativeTo(null);
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		progresso.setToolTipText("Aguarde...");
		setTitle("Carregando");

		LeitorDeArquivo leitor = new LeitorDeArquivo();
		ArquivoSPED arquivo = new ArquivoSPED();
		CorretorSPED corretorSPED = new CorretorSPED();
		File file = EscolhedorDeArquivo.escolher();
		setVisible(true);
		progresso.setValue(10); // Controle de progresso

		arquivo = leitor.montaArquivoSPED(file);
		progresso.setValue(50); // Controle de progresso
	//	corretorSPED.setArquivoSPED(arquivo);
		corretorSPED.corrigirArquivo(arquivo);
		progresso.setValue(75); // Controle de progresso

		arquivo.salvarArquivo();
		progresso.setValue(100); // Controle de progresso

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		setVisible(false);
		JOptionPane.showMessageDialog(this, "arquivo corrigido salvo em C:/Users/Junior/Desktop");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new Programa();

	}
}