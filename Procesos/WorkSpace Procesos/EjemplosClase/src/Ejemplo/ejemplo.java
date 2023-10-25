package Ejemplo;
import java.util.Iterator;

public class ejemplo {

	public static void main(String[] args) {
		
		int contador = 0;
		miContador contador = new miContador(0);
		
		procesosContador pc1 =  new procesosContador("primero", contador);
		pc1.start();
		procesosContador pc2 =  new procesosContador("segundo", contador);
		pc2.start();
		procesosContador pc3 =  new procesosContador("tercero", contador);
		pc2.start();
		
		
		for (int i = 0; i < 10; i++) {
			int temp = contadorComun.getConstructor();
			temp++;
			System.out.println("Proceso padre: " + i  + " - " + procesosContador.getContador());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Pinga salio mal");
			}
		}
	}
}
