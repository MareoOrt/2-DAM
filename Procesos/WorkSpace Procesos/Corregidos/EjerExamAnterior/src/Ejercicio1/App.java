package Ejercicio1;

import java.util.Timer;

public class App {

	public static void main(String[] args) {
		ContadorGeneral cg = new ContadorGeneral(0);
		Procesos procesos[]=new Procesos[4];
		for (int i = 0; i <4; i++) {
			String nombre= "Proceso "+i;
			procesos[i]= new Procesos(nombre, cg);
			procesos[i].start();
		}
		
		Cronometro cronometro= new Cronometro(cg);
		cronometro.start();
		
		
		
		int i=0;
		while(i<4)
		{
			if (procesos[i].isAlive())
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				i++;
		}


		cronometro.setBandera(false);
		System.out.println("-----"+cronometro.getSegundos());
		

	}

}
