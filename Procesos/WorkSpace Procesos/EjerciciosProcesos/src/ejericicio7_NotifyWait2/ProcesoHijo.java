package ejericicio7_NotifyWait2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProcesoHijo extends Thread {

	private CadNumero numeros;
	private boolean elegido = false;
	private List<Integer> cadNumElegidos = new ArrayList<Integer>();

	public ProcesoHijo(CadNumero numeros) {
		super();
		this.numeros = numeros;
	}
	

	public boolean isElegido() {
		return elegido;
	}


	public void setElegido(boolean elegido) {
		this.elegido = elegido;
	}


	@Override
	public void run() {
		super.run();

		do {
			while(!isInterrupted()) {
			synchronized (numeros) {

				try {
					numeros.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					
				}
				
				if(numeros.getNumeros().size()<=0) {
					System.out.println("---------------------------------------");
				}
				
				int indiceNElegido = (new Random().nextInt(0, numeros.getNumeros().size()));

				int n = numeros.getNumeros().get(indiceNElegido);
				System.out.println("Soy el proceso hijo " + getName() + " y escogi el numero " + n);
				numeros.getNumeros().remove(indiceNElegido);
				cadNumElegidos.add(n);
				elegido = true;
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}}
		} while (cadNumElegidos.size() > 5);

	}
}
