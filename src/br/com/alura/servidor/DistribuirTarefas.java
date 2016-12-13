package br.com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;

	public DistribuirTarefas(ExecutorService threadPool, BlockingQueue<String> filaComandos, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
		this.filaComandos = filaComandos;
		this.socket = socket;
		this.servidor = servidor;
		this.iniciarConsumidores();
	}

	private void iniciarConsumidores() {
		
		int quantidadeConsumidores = 2;
		for (int i = 0; i < quantidadeConsumidores; i++) {
			
			TarefaConsumir consumir = new TarefaConsumir(filaComandos);
			this.threadPool.execute(consumir);
		}
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

					ComandoC2AcessaBanco acessaBanco = new ComandoC2AcessaBanco(saidaCliente);
					ComandoC2ChamaWS chamadaWS = new ComandoC2ChamaWS(saidaCliente);
					
					Future<String> futureDB = this.threadPool.submit(acessaBanco);
					Future<String> futureWS = this.threadPool.submit(chamadaWS);
					
					JuntaResultadosFutureWSFutureBanco juntaResultados = new JuntaResultadosFutureWSFutureBanco(futureWS, futureDB, saidaCliente);
					this.threadPool.submit(juntaResultados);

					break;
				case "c3":
					
					System.out.println("Adicionando comando c3 na fila. Origem: " + socket.getPort());
					this.filaComandos.put(comando);
					saidaCliente.print("Comando c3 adicionado na fila.");
					System.out.println("Comando c3 adicionado na fila. Origem: " + socket.getPort());
					
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
