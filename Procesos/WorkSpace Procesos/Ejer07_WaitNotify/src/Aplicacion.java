
public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatoCompartido dato=new DatoCompartido();
		Productor p=new Productor(dato);
		p.start();
		for(int i=0;i<5;i++)
		{
			Consumidor c=new Consumidor("Proceso" + i, dato);
			c.start();
		}
		
	}

}
