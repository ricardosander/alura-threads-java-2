package br.com.alura.servidor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class MostraHora implements Runnable {

	@Override
	public void run() {
		
		LocalDateTime now = LocalDateTime.now();
		String hora = now.format(DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm"));
		
		System.out.println("Data e hora atual: " + hora);
		
		Set<Thread> todasThreads = Thread.getAllStackTraces().keySet();
		
		System.out.println("Threads:");
		for (Thread t : todasThreads) {
			System.out.println(t.getName());
		}
		System.out.println("");
	}

}
