//package jercicioConcurrenciaBasica;
//
//import java.util.Iterator;
//import java.util.Scanner;
//
//import Ejemplo.procesosContador;
//
//public class EjercicioConcurrenciaBasica {
//	
////		1. EJERCICIO CONCURRENCIA BÁSICA
////		Pedir al usuario hasta qué número queremos contar, de cuánto en cuánto y cuántos procesos 
////		vamos a utilizar para ello, y por último mostrar cuántas veces ha contado cada uno de los 
////		procesos. Implementarlo. 
//	
//	procesosContador pc =  new procesosContador("primero", contador);
//	pc1.start();
//	
//	public static void main(String[] args) {
//		
//		inicial();
//	}
//	
//	private static void inicial() {
//		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("Hasta que numero quieres llegar?");
//		int numeroFinal = sc.nextInt();
//		
//		System.out.println("de cuanto en cuanto?");
//		int intervalCont = sc.nextInt();
//		
//		System.out.println("Y cuantos procesos?");
//		int nProcess = sc.nextInt();
//		
//		contProcess(numeroFinal, intervalCont, nProcess);
//	}
//	
//	private static void contProcess(int numeroFinal, int intervalCont, int nProcess) {
//		// TODO Auto-generated method stub
//		for (int i = 0; i < numeroFinal; i = i + intervalCont) {
//			System.out.println(i);
//		}
//	}
//	
//	
//}
