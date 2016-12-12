package br.com.alura.cliente;

import java.net.Socket;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12345);

		System.out.println("Conexão estabelecida.");
		
		Thread threadEnviaComando = new Thread(new EnviadorDeComandos(socket));
		Thread threadRecebeResposta = new Thread(new RecebedorDeResposta(socket));

		threadEnviaComando.start();
		threadRecebeResposta.start();
		
		threadEnviaComando.join();
		
		System.out.println("Encerrando comunicação.");
		
		socket.close();
	}
}
