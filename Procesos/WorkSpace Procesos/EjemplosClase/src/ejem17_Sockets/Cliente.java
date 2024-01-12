package ejem17_Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		
		// EL 127.0.0.1 lo tienen todos
		// localhost es el que esta puesto por defecto
		try (Socket socket = new Socket("localhost", 4444);){
			// Flujo de entrada de datos en bytes
			InputStream is = socket.getInputStream();
			
			// Leer en objetos
			ObjectInputStream ois = new ObjectInputStream(is);
			
			
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject("Recibido!");
			
			System.out.println(ois.readObject());			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
