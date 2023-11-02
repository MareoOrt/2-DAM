

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Contador contador = new Contador(0);
		ThreadGroup grupo=new ThreadGroup("hijos");

		for (int i = 0; i < 5; i++) {
			new Proceso(contador, String.valueOf(i),grupo);
		}
	}

}
