package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {
	
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
		
		System.out.println("---- Iniciando Servidor -----");
		ServerSocket serverSocket = new ServerSocket(12345);
		
		threadPool.scheduleAtFixedRate(new MostraHora(), 0, 1, TimeUnit.MINUTES);
		while (true) {
			
			Socket socket = serverSocket.accept();
			
			System.out.println("Aceitando novo cliente na porta " + socket.getPort());
			threadPool.execute(new DistribuirTarefas(socket));
		}
	}
}
