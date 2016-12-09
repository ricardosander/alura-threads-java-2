package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {
	
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		System.out.println("---- Iniciando Servidor -----");
		ServerSocket serverSocket = new ServerSocket(12345);
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new MostraHora(), 0, 30, TimeUnit.SECONDS);
		while (true) {
			
			Socket socket = serverSocket.accept();
			
			System.out.println("Aceitando novo cliente na porta " + socket.getPort());
			threadPool.execute(new DistribuirTarefas(socket));
		}
	}
}
