package br.com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;

	public DistribuirTarefas(Socket socket, ServidorTarefas servidor) {
		this.socket = socket;
		this.servidor = servidor;
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
				
				if (comando.trim().toLowerCase().equals("end")) {
					System.out.println("Desligando servidor...");
					this.servidor.parar();
				}
				
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
