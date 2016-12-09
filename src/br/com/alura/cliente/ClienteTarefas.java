package br.com.alura.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12345);

		System.out.println("Conexão estabelecida.");

		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(socket.getOutputStream());

		System.out.println("(quit para sair)Digite o comando: ");

		String comando;
		do {

			comando = teclado.nextLine();
			saida.println(comando);
		} while (!comando.toLowerCase().equals("quit"));

		teclado.close();
		saida.close();
		socket.close();
	}
}
