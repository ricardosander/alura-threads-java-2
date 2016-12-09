package br.com.alura.servidor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MostraHora implements Runnable {

	@Override
	public void run() {
		
		LocalDateTime now = LocalDateTime.now();
		String hora = now.format(DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm"));
		
		System.out.println("Data e hora atual: " + hora);
	}

}
