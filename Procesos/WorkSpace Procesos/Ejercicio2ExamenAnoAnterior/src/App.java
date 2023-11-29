

public class App {

	public static void main(String[] args) {
		
		NumeroCompartido numeroCompartido = new NumeroCompartido(100);
		
		ProcesoContador procesoContador = new ProcesoContador("Proceso contador", numeroCompartido);
		procesoContador.getThread().start();
		
		
		ProcesoPrincipal procesoPrincipal = new ProcesoPrincipal(numeroCompartido);
		procesoPrincipal.getThread().start();



	}
}
