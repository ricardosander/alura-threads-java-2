package br.com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;

	public DistribuirTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
		this.socket = socket;
		this.servidor = servidor;
	}

	@Override
	public void run() {

		System.out.println("\n\n" + socket.getPort() + " está online.");

		try {

			Scanner entradaCliente = new Scanner(this.socket.getInputStream());
			PrintStream saidaCliente = new PrintStream(this.socket.getOutputStream());

			String comando;
			while (entradaCliente.hasNextLine()) {

				comando = entradaCliente.nextLine();

				System.out.println("\nComando enviado por " + this.socket.getPort() + ": " + comando);

				switch (comando) {
				case "c1":

					saidaCliente.println("Confirmação do comando c1");

					ComandoC1 c1 = new ComandoC1(saidaCliente);
					this.threadPool.execute(c1);

					break;
				case "c2":

					saidaCliente.println("Confirmação do comando c2");

					ComandoC2 c2 = new ComandoC2(saidaCliente);
					this.threadPool.execute(c2);

					break;
				case "fim":
					
					System.out.println("\n\nDesligando servidor...");
					this.servidor.parar();
					break;

				default:

					System.out.println("\nComando inválido executado.");
					saidaCliente.println("Comando informado não encontrado.");
					break;
				}

				saidaCliente.println(comando);
			}

			saidaCliente.close();
			entradaCliente.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("\n\n" + socket.getPort() + " se desconectou.");
		}
	}

}
