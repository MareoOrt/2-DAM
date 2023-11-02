package Ejericicio5.Ejercicio_Matando_Procesos_Runnable;

public class Ejericicio5 {
	public static void main(String[] args) {
		
		Contador c = new Contador(0);
		ThreadGroup grupo = new ThreadGroup("hijos");
		
		for (int i = 0; i < 5; i++) {
			
			new Proceso(c, String.valueOf(i), grupo);
		}
	}
}
