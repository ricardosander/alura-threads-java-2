package br.com.alura.cliente.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterfaceGrafica {

	private JFrame janela;
	private JTextArea campoDeEnvio;
	private JTextArea campoDeResposta;
	private final PrintStream saida;

	public InterfaceGrafica(Socket socket) throws IOException {
		this.saida = new PrintStream(socket.getOutputStream());
	}

	public void montaTela() {

		preparaJanela();
		preparaEnviador();
		preparaReceptor();
		mostraJanela();
	}

	public void imprime(String texto) {
		String novaLinha = System.getProperty("line.separator");
		campoDeResposta.append(texto + novaLinha);
	}

	private void preparaEnviador() {

		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Envio de Mensagens"));

		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		container.add(criaCampoDeEnvio());

		JButton botaoEnviar = new JButton("Enviar");
		botaoEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String text = campoDeEnvio.getText();
				if (!text.trim().isEmpty()) {
					
					saida.println(text);
					campoDeEnvio.setText("");
				}
			}
		});
		container.add(botaoEnviar);

		janela.add(container);
	}

	private void preparaReceptor() {

		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Resposta Servidor"));

		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		container.add(criaCampoDeResposta());

		janela.add(container);
	}

	private void mostraJanela() {
		
		janela.setLayout(new GridLayout(2, 1));
		janela.pack();
		janela.setVisible(true);
	}

	private void preparaJanela() {

		janela = new JFrame("Cliente Servidor-Tarefas");
		janela.setMinimumSize(new Dimension(600, 400));
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JScrollPane criaCampoDeResposta() {

		this.campoDeResposta = new JTextArea();
		this.campoDeResposta.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.campoDeResposta);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scroll;
	}

	private JScrollPane criaCampoDeEnvio() {

		this.campoDeEnvio = new JTextArea();
		this.campoDeEnvio.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.campoDeEnvio);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scroll;
	}

}
