package Ejem7_Notify;

import Ejemplo6_JoinYieldDaemon.Process;

public class App {
	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			Process p = new Process();
			
			// El metodo daemon tiene que lanzarse antes de ser lanzado, y este hace
			// que al acabar el padre mueran el resto de procesos con el
			System.out.println("CON DAEMON ---------------------------");
			// Daemon
			p.setDaemon(true);

			p.start();
			
			System.out.println("CON JOIN ---------------------------");
			// Join
			try {
				// Funciona como un sleep hasta que se acaba el proceso
				p.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("El porceso principal finaliza");
			
			// El wait le pasa a un tiempo de espera que acabara cuando se le haga un notify o notifyAll
		}
	}
}
