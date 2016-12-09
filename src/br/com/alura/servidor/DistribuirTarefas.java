package br.com.alura.servidor;

import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

	private Socket socket;

	public DistribuirTarefas(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {

		System.out.println("\n\n" + socket.getPort() + " entrou na sala.");

		try {
			
			Scanner entradaCliente = new Scanner(this.socket.getInputStream());
			
			while (entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				
				System.out.println("\n" + socket.getPort() + " falou:");
				System.out.println(comando);
			}
			entradaCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("\n\n" + socket.getPort() + " saiu da sala.");
		}
	}

}
