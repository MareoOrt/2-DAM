package versionPro;

import java.util.ArrayList;

public class Ventanilla {
	private double dinero;
	private boolean seAcaboElChollo;

	public Ventanilla() {
		super();
		this.dinero = 0;
		seAcaboElChollo = false;
	}

	public synchronized boolean isSeAcaboElChollo() {
		return seAcaboElChollo;
	}

	public synchronized void setSeAcaboElChollo(boolean seAcaboElChollo) {
		notifyAll();
		this.seAcaboElChollo = seAcaboElChollo;
	}

	public synchronized boolean ventanillaVacia() {
		return  (this.dinero ==0);
	}
	
	public synchronized double getDinero() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double d = this.dinero;
		this.dinero = 0;
		return d;
	}

	public synchronized void setDinero(double dinero) {
		this.dinero = dinero;
		notify();
	}

}
