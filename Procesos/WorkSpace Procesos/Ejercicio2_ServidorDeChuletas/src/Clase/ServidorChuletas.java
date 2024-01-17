package Clase;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServidorChuletas {

	public static void main(String[] args) {

		int nAlumnos = 5;
		boolean b = true;

		try (ServerSocket sv = new ServerSocket(4444)) {

			ArrayList<Socket> listAlumnos = new ArrayList<Socket>();

			for (int i = 0; i < nAlumnos; i++) {
				listAlumnos.add(sv.accept());
				System.out.println("Un alumno entro al servidor.");
			}

			do {
				Scanner sc = new Scanner(System.in);
				System.out.println("Cual es la chuleta?");
				String chuleta = sc.nextLine();

				if (!chuleta.contentEquals("SUSPENSO")) {

					for (Socket alumno : listAlumnos) {
						System.out.println("Chuleta: " + chuleta);
						
						OutputStream os = alumno.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(os);
						oos.writeObject(chuleta);
						oos.flush();
					}

					for (Socket alumno : listAlumnos) {
						InputStream is = alumno.getInputStream();
						ObjectInputStream ois = new ObjectInputStream(is);
						System.out.println(ois.readObject());
					}
					
				} else {
					b = false;
					System.out.println("Soy el profesor y os he pillado. SUSPENSO");
				}

			
			} while (b);
			System.out.println("El profesor desconecto el servidor.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("\n - Error:\n" + e);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("\n - Error:\n" + e);
		}
	}
}
