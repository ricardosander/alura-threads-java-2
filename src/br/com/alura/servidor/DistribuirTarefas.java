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

		System.out.println("Distribuindo as tarefas para o cliente " + socket.getPort());

		try {
			
			Scanner entradaCliente = new Scanner(this.socket.getInputStream());
			
			while (entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				System.out.println("Executando comando: \n" + comando + "\n por " + this.socket.getPort());
			}
			entradaCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
