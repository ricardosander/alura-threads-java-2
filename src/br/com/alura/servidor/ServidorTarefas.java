package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {
		
		System.out.println("---- Iniciando Servidor -----");
		ServerSocket serverSocket = new ServerSocket(12345);
		while(true) {
			
			Socket socket = serverSocket.accept();
			
			System.out.println("Aceitando novo cliente na porta " + socket.getPort());

		}
	}
}
