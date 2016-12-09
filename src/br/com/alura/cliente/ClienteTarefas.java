package br.com.alura.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("localhost", 12345);
		
		System.out.println("Conexão estabelecida.");
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite o comando: ");
		String comando = teclado.nextLine();
		PrintStream saida = new PrintStream(socket.getOutputStream());
		saida.println(comando);
		
		teclado.close();
		saida.close();
		socket.close();
	}
}
