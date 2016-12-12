package br.com.alura.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviadorDeComandos implements Runnable {

	private Socket socket;

	public EnviadorDeComandos(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			
			Scanner teclado = new Scanner(System.in);
			PrintStream saida;

			saida = new PrintStream(this.socket.getOutputStream());

			System.out.println("\n\nComandos suportados: "
					+ "\nc1 - faz c1"
					+ "\nc2 - faz c2"
					+ "\nfim - programa servidor para finalização"
					+ "\nquit: desconecta-se do servidor"
					+ "\n\nDigite o comando: ");

			String comando;
			do {

				comando = teclado.nextLine();
				saida.println(comando);
				
			} while (!comando.toLowerCase().equals("quit"));

			teclado.close();
			saida.close();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
