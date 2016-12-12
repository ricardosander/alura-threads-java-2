package br.com.alura.servidor;

import java.io.PrintStream;
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
			PrintStream saidaCliente = new PrintStream(this.socket.getOutputStream());
			
			String comando;
			String saida;
			while (entradaCliente.hasNextLine()) {
				
				comando = entradaCliente.nextLine();
				saida = "\n" + socket.getPort() + " falou:";
				
				System.out.println(saida);
				System.out.println(comando);
				
				saidaCliente.println(saida);
				saidaCliente.println(comando);
			}
			
			saidaCliente.close();
			entradaCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("\n\n" + socket.getPort() + " saiu da sala.");
		}
	}

}
