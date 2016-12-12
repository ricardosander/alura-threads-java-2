package br.com.alura.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RecebedorDeResposta implements Runnable {

	private Socket socket;

	public RecebedorDeResposta(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			
			System.out.println("Recebendo respostas do servidor");
			
			Scanner respostaServidor = new Scanner(socket.getInputStream());
			
			String linha;
			while (respostaServidor.hasNext()) {
				linha = respostaServidor.nextLine();
				System.out.println(linha);
			}
			
			respostaServidor.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
