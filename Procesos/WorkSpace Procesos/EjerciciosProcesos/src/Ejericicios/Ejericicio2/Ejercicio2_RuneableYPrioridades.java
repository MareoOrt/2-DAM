package Ejericicios;

import Proceso.ProcesoRunneable;

public class Ejercicio2_RuneableYPrioridades {

//	2. EJERCICIO . RUNNABLE Y PRIORIDADES
//	Realizar un programa que cuente hasta 100000 entre tres procesos y que al finalizar muestre 
//	un resumen de las veces que ha contado cada uno. Debemos tener en cuenta que cada proceso 
//	tiene prioridades distintas, y tendrá que informarse en el resumen final. Implementar la clase 
//	Runnable para realizarlo. Hacerlo con los procesos sincronizados y sin sincronizarlos. ¿Cuenta 
//	más veces el proceso que tiene más prioridad? 

	public static void main(String[] args) {
		
		ProcesoRunneable proceso1 = new ProcesoRunneable("proceso1");
		ProcesoRunneable proceso2 = new ProcesoRunneable("proceso2");
		ProcesoRunneable proceso3 = new ProcesoRunneable("proceso3");
		
		
	}
}
