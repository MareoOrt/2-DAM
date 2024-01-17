package Clase;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Alumno {

	public static void main(String[] args) throws ClassNotFoundException {

		boolean b = true;
		try (Socket socket = new Socket("localhost", 4444);) {
			do {

				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);

				OutputStream os = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);

				oos.writeObject("Gracias!");

			} while (b);
			
			System.out.println("Adios al alumno.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("\n - Error:\n" + e);
		}
	}
}
