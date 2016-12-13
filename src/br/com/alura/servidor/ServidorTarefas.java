package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

	private ServerSocket servidor;
	private ExecutorService threadPool;
	private AtomicBoolean estaRodando;
	private BlockingQueue<String> filaComandos;

	public ServidorTarefas() throws IOException {

		System.out.println("---- Iniciando Servidor -----");
		this.servidor = new ServerSocket(12345);
		this.threadPool = Executors.newCachedThreadPool(new FabricaDeThreads(Executors.defaultThreadFactory()));
//		this.threadPool = Executors.newFixedThreadPool(2, new FabricaDeThreads(Executors.defaultThreadFactory()));
		this.estaRodando = new AtomicBoolean(true);
		this.filaComandos = new ArrayBlockingQueue<>(2);
	}

	public void rodar() throws IOException {

//		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new MostraHora(), 0, 30, TimeUnit.SECONDS);
		while (this.estaRodando.get()) {

			try {
				
				Socket socket = this.servidor.accept();
				System.out.println("\nAceitando novo cliente na porta " + socket.getPort());
				threadPool.execute(new DistribuirTarefas(this.threadPool, this.filaComandos, socket, this));

			} catch (SocketException e) {
				System.out.println("SocketException, está rodando? " + this.estaRodando);
			}
		}
	}

	public void parar() throws IOException {

		System.out.println("\n\n\nParando servidor.");
		this.estaRodando.set(false);
		this.threadPool.shutdown();
		this.servidor.close();
	}

	public static void main(String[] args) throws IOException {

		ServidorTarefas servidor = new ServidorTarefas();
		servidor.rodar();

	}
}
