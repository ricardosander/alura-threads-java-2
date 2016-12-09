package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {
	
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		System.out.println("---- Iniciando Servidor -----");
		ServerSocket serverSocket = new ServerSocket(12345);
		while(true) {
			
			Socket socket = serverSocket.accept();
			
			System.out.println("Aceitando novo cliente na porta " + socket.getPort());
			threadPool.execute(new DistribuirTarefas(socket));
		}
	}
}
