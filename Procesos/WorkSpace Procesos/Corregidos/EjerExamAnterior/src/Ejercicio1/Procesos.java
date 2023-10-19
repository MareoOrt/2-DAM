package Ejercicio1;


public class Procesos  extends Thread{
private ContadorGeneral contadorGeneral;


public Procesos(String nombre, ContadorGeneral contador) {
	super(nombre);
	this.contadorGeneral=contador;
}


public ContadorGeneral getContadorGeneral() {
	return contadorGeneral;
}


public void setContadorGeneral(ContadorGeneral contadorGeneral) {
	this.contadorGeneral = contadorGeneral;
}
@Override
public void run() {
	// TODO Auto-generated method stub
	super.run();
	int contadorIndividual= 0;
	boolean  finalizacion=false;
	do {
		synchronized (contadorGeneral) {
			System.out.println("Veces contadas: "+contadorGeneral.getContador()+ "\n"+ this.getName()+ " lleva contadas  "+ contadorIndividual+ " veces.");
			contadorGeneral.setContador(contadorGeneral.getContador()+1);
		}
		contadorIndividual++;
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (contadorIndividual==5000) {
			finalizacion=true;
		}
	
	} while (contadorGeneral.getContador()<20000 && !finalizacion);
	
	System.out.println("El proceso " + this.getName() + " ha contado " + contadorIndividual + " veces");
}




}
