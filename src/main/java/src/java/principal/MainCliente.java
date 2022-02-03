package src.java.principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import pojo.Persona;

public class MainCliente {
	
	private static Scanner scanner;
	private static String nombre;
	private static int edad;
	private static Persona persona;
	private static ObjectOutputStream flujoSalida;
	private static ObjectInputStream flujoEntrada;
	private static Socket cliente;

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		String host = "localhost";
		int puerto = 6000;
		cliente = new Socket(host, puerto);
		System.out.println("Cliente iniciado...");
		
		scanner = new Scanner(System.in);
		
		mandarObjetosPersona();
		leerNombrePersonasRecibidas();
		
		flujoEntrada.close();
		flujoSalida.close();
		cliente.close();
		
	}

	private static void mandarObjetosPersona() throws IOException {
		
		for(int i = 0; i < 5; i++) {
			System.out.println("Introduzca el nombre de la persona que desea enviar al Servidor: ");
			nombre = scanner.next();
			System.out.println("Introduzca una edad para la persona: ");
			edad = scanner.nextInt();
			persona = new Persona(nombre, edad);
			flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
			flujoSalida.writeObject(persona);
			System.out.println("La persona enviada es: " + persona.getNombre() + " " + persona.getEdad());
		}
		
	}
	
	private static void leerNombrePersonasRecibidas() throws IOException, ClassNotFoundException {
		
		flujoEntrada = new ObjectInputStream(cliente.getInputStream());
		persona = new Persona(nombre, edad);
		Persona persona = (Persona)flujoEntrada.readObject();
		
	}


}
